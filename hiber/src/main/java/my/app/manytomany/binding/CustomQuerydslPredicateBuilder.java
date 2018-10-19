package my.app.manytomany.binding;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.Property;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.util.TypeInformation;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.function.Consumer;

public class CustomQuerydslPredicateBuilder {

    private final ConversionService conversionService;
    //    private final MultiValueBinding<Path<? extends Object>, Object> defaultBinding;
    private final Map<PathInformation, Path<?>> paths;
    private final EntityPathResolver resolver;
    private final String operator = "op";

    public CustomQuerydslPredicateBuilder(ConversionService conversionService, EntityPathResolver resolver) {

        Assert.notNull(conversionService, "ConversionService must not be null!");

//        this.defaultBinding = ;
        this.conversionService = conversionService;
        this.paths = new HashMap<>();
        this.resolver = resolver;
    }

    @Nullable
    public Predicate getPredicate(TypeInformation<?> type, MultiValueMap<String, String> keyValues,
                                  CustomQuerydslBindings bindings) {

        Assert.notNull(bindings, "Context must not be null!");

        BooleanBuilder builder = new BooleanBuilder();
        Consumer<Predicate> joiner = getPredicatesJoiner(keyValues, builder);

        if (keyValues.isEmpty()) {
            return builder;
        }

        for (Map.Entry<String, List<String>> entry : keyValues.entrySet()) {

            if (isSingleElementCollectionWithoutText(entry.getValue())) {
                continue;
            }

            PathOperatorPair pathOperatorPair = PathOperatorPair.fromString(entry.getKey());
            String path = pathOperatorPair.getPath();
            Ops operator = pathOperatorPair.getOperator();

            if (!bindings.isPathAvailable(path, type)) {
                continue;
            }

            PathInformation propertyPath = bindings.getPropertyPath(path, type);

            if (propertyPath == null) {
                continue;
            }

            Collection<Object> value = convertToPropertyPathSpecificType(entry.getValue(), propertyPath);
            Optional<Predicate> predicate = invokeBinding(operator, propertyPath, bindings, value);

            if (predicate.isPresent()) {
                joiner.accept(predicate.get());
            } else {
                DefaultBinder.bind(operator, joiner, getPath(propertyPath, bindings), value);
            }
        }

        return builder.getValue();
    }

    private Consumer<Predicate> getPredicatesJoiner(MultiValueMap<String, String> keyValues, BooleanBuilder builder) {
        boolean operatorPresent = keyValues.keySet().stream().anyMatch(key -> key.equalsIgnoreCase(operator));
        if (operatorPresent) {
            if ("OR".equalsIgnoreCase(keyValues.getFirst(operator))) {
                return builder::or;
            }
        }
        return builder::and;
    }


    private Optional<Predicate> invokeBinding(Ops operator, PathInformation dotPath, CustomQuerydslBindings bindings,
                                              Collection<Object> values) {

        Path<?> path = getPath(dotPath, bindings);
        return bindings.getBindingForPathAndOperator(operator, dotPath).flatMap(bs -> bs.bind(path, values));
    }

    private Path<?> getPath(PathInformation path, CustomQuerydslBindings bindings) {

        Optional<Path<?>> resolvedPath = bindings.getExistingPath(path);

        return resolvedPath.orElseGet(() -> paths.computeIfAbsent(path, it -> it.reifyPath(resolver)));
    }

    private static boolean isSingleElementCollectionWithoutText(List<String> source) {
        return source.size() == 1 && !StringUtils.hasText(source.get(0));
    }

    private Collection<Object> convertToPropertyPathSpecificType(List<String> source, PathInformation path) {

        Class<?> targetType = path.getLeafType();

        if (source.isEmpty() || isSingleElementCollectionWithoutText(source)) {
            return Collections.emptyList();
        }

        Collection<Object> target = new ArrayList<>(source.size());

        for (String value : source) {

            target.add(conversionService.canConvert(String.class, targetType)
                    ? conversionService.convert(value, TypeDescriptor.forObject(value), getTargetTypeDescriptor(path))
                    : value);
        }

        return target;
    }

    private static TypeDescriptor getTargetTypeDescriptor(PathInformation path) {

        PropertyDescriptor descriptor = path.getLeafPropertyDescriptor();

        Class<?> owningType = path.getLeafParentType();
        String leafProperty = path.getLeafProperty();

        TypeDescriptor result = descriptor == null //
                ? TypeDescriptor
                .nested(org.springframework.data.util.ReflectionUtils.findRequiredField(owningType, leafProperty), 0)
                : TypeDescriptor
                .nested(new Property(owningType, descriptor.getReadMethod(), descriptor.getWriteMethod(), leafProperty), 0);

        if (result == null) {
            throw new IllegalStateException(String.format("Could not obtain TypeDesciptor for PathInformation %s!", path));
        }

        return result;
    }
}

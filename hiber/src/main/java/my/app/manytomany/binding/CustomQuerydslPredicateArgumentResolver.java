package my.app.manytomany.binding;

import com.querydsl.core.types.Predicate;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.util.CastUtils;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class CustomQuerydslPredicateArgumentResolver implements HandlerMethodArgumentResolver {

    private final CustomQuerydslBindingsFactory bindingsFactory;
    private final CustomQuerydslPredicateBuilder predicateBuilder;

    public CustomQuerydslPredicateArgumentResolver(CustomQuerydslBindingsFactory factory, ConversionService conversionService) {
        this.bindingsFactory = factory;
        this.predicateBuilder = new CustomQuerydslPredicateBuilder(Optional.ofNullable(conversionService).orElseGet(DefaultConversionService::new),
                factory.getEntityPathResolver());
    }

    @Override
    public Predicate resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                     NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String[]> entry : webRequest.getParameterMap().entrySet()) {
            parameters.put(entry.getKey(), Arrays.asList(entry.getValue()));
        }

        Optional<CustomQuerydslPredicate> annotation = Optional
                .ofNullable(parameter.getParameterAnnotation(CustomQuerydslPredicate.class));
        TypeInformation<?> domainType = extractTypeInfo(parameter).getRequiredActualType();

        Optional<Class<? extends CustomQuerydslBinderCustomizer<?>>> customizer = annotation//
                .map(CustomQuerydslPredicate::bindings)//
                .map(CastUtils::cast);

        return predicateBuilder.getPredicate(domainType, parameters,
                customizer.map(it -> bindingsFactory.createBindingsFor(domainType, it))
                        .orElseGet(() -> bindingsFactory.createBindingsFor(domainType)));

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        if (Predicate.class.equals(parameter.getParameterType())) {
            return true;
        }

        if (parameter.hasParameterAnnotation(CustomQuerydslPredicate.class)) {
            throw new IllegalArgumentException(String.format("Parameter at position %s must be of type Predicate but was %s.",
                    parameter.getParameterIndex(), parameter.getParameterType()));
        }

        return false;
    }

    /**
     * Obtains the domain type information from the given method parameter. Will favor an explicitly registered on through
     * {@link QuerydslPredicate#root()} but use the actual type of the method's return type as fallback.
     *
     * @param parameter must not be {@literal null}.
     * @return
     */
    static TypeInformation<?> extractTypeInfo(MethodParameter parameter) {

        Optional<CustomQuerydslPredicate> annotation = Optional
                .ofNullable(parameter.getParameterAnnotation(CustomQuerydslPredicate.class));

        return annotation.filter(it -> !Object.class.equals(it.root()))//
                .<TypeInformation<?>>map(it -> ClassTypeInformation.from(it.root()))//
                .orElseGet(() -> detectDomainType(parameter));
    }

    private static TypeInformation<?> detectDomainType(MethodParameter parameter) {

        Method method = parameter.getMethod();

        if (method == null) {
            throw new IllegalArgumentException("Method parameter is not backed by a method!");
        }

        return detectDomainType(ClassTypeInformation.fromReturnTypeOf(method));
    }

    private static TypeInformation<?> detectDomainType(TypeInformation<?> source) {

        if (source.getTypeArguments().isEmpty()) {
            return source;
        }

        TypeInformation<?> actualType = source.getActualType();

        if (actualType == null) {
            throw new IllegalArgumentException(String.format("Could not determine domain type from %s!", source));
        }

        if (source != actualType) {
            return detectDomainType(actualType);
        }

        if (source instanceof Iterable) {
            return source;
        }

        return detectDomainType(source.getRequiredComponentType());
    }
}

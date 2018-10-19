/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package my.app.manytomany.binding;

import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.CollectionPathBase;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

//EQ and LIKE supported ONLY
class DefaultBinder {
//    private Consumer<Predicate> joiner;
//    private Ops operator;
//
//    public DefaultBinder(Consumer<Predicate> joiner, Ops operator) {
//        this.joiner = joiner;
//        this.operator = operator;
//    }

    public static void main(String[] args) {
        Stream<String> stringStream = Lists.newArrayList("a", "b", "c").stream();
//        ArrayList<String> collect = stringStream
//                .collect(() -> new ArrayList<String>(),
//                        (res, el) -> res.add(el),
//                        (a, b) -> {}
//                );
        List<String> asList = stringStream.collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
        System.err.println(asList);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.web.querydsl.QueryDslPredicateBuilder#buildPredicate(org.springframework.data.mapping.PropertyPath, java.lang.Object)
     */
//    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void bind(Ops operator, Consumer<Predicate> joiner, Path<?> path, Collection<? extends Object> value) {

        Assert.notNull(path, "Path must not be null!");
        Assert.notNull(value, "Value must not be null!");

        if (value.isEmpty()) {
            return;
        }

        if (path instanceof CollectionPathBase) {

            BooleanBuilder builder = new BooleanBuilder();

            for (Object element : value) {
                builder.and(((CollectionPathBase) path).contains(element));
            }

            joiner.accept(builder.getValue());
            return;
        }

        if (path instanceof StringExpression && operator.equals(Ops.LIKE)) {
            value.forEach(val -> joiner.accept(((StringExpression) path).like("%" + val + "%")));
            return;
        }

        if (path instanceof SimpleExpression) {

            if (value.size() > 1) {
                joiner.accept(((SimpleExpression) path).in(value));
                return;
            }
            joiner.accept(((SimpleExpression) path).eq(value.iterator().next()));
            return;
        }

        throw new IllegalArgumentException(
                String.format("Cannot create predicate for path '%s' with type '%s'.", path, path.getMetadata().getPathType()));
    }
}

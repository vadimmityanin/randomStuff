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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to customize the binding of HTTP request parameters to a Querydsl
 * {@link com.querydsl.core.types.Predicate} in Spring MVC handler methods.
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomQuerydslPredicate {

	/**
	 * The root type to create the {@link com.querydsl.core.types.Predicate}. Specify this explicitly if the type is not
	 * contained in the controller method's return type.
	 *
	 * @return
	 */
	Class<?> root() default Object.class;

	/**
	 * To customize the way individual properties' values should be bound to the predicate a
	 * {@link CustomQuerydslBinderCustomizer} can be specified here. We'll try to obtain a Spring bean of this type but fall
	 * back to a plain instantiation if no bean is found in the current
	 * {@link org.springframework.beans.factory.BeanFactory}.
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends CustomQuerydslBinderCustomizer> bindings() default CustomQuerydslBinderCustomizer.class;
}

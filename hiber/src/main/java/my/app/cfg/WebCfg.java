package my.app.cfg;

import my.app.manytomany.binding.CustomQuerydslBindingsFactory;
import my.app.manytomany.binding.CustomQuerydslPredicateArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
//@EnableWebMvc
public class WebCfg extends DelegatingWebMvcConfiguration {

    @Autowired
    private CustomQuerydslBindingsFactory factory;
    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    @Bean
    public CustomQuerydslPredicateArgumentResolver myQuerydslPredicateArgumentResolver() {
        return new CustomQuerydslPredicateArgumentResolver(factory, conversionService);
    }

    @Bean
    public CustomQuerydslBindingsFactory customQuerydslBindingsFactory() {
        return new CustomQuerydslBindingsFactory(SimpleEntityPathResolver.INSTANCE);
    }

    @Override
    protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter() {
            @Override
            public void setCustomArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                List<HandlerMethodArgumentResolver> res = argumentResolvers.stream()
                        .filter(r -> !r.getClass().equals(QuerydslPredicateArgumentResolver.class)).collect(Collectors.toList());
                res.add(0, myQuerydslPredicateArgumentResolver());
                super.setCustomArgumentResolvers(res);
            }
        };
    }



//    @Bean
//    @Override
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter handlerAdapter = super.requestMappingHandlerAdapter();
//        ofNullable(handlerAdapter.getArgumentResolvers())
//                .ifPresent(list -> list
//                        .removeIf(resolver -> resolver.getClass().equals(QuerydslPredicateArgumentResolver.class)));
//        return handlerAdapter;
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(0, myQuerydslPredicateArgumentResolver());
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.set(0, querydslPredicateArgumentResolver());
//    }
}

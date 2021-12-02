package com.example.foodshop.config;

import com.example.foodshop.web.interceptor.CategoryInterceptor;
import com.example.foodshop.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;
    private final CategoryInterceptor interceptor;



    public WebConfiguration(StatsInterceptor statsInterceptor, CategoryInterceptor interceptor) {
        this.statsInterceptor = statsInterceptor;

        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(statsInterceptor);
        registry.addInterceptor(interceptor);

    }
}

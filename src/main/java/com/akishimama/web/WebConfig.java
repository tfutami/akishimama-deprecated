package com.akishimama.web;

import com.akishimama.web.filter.WebSessionFilter;
import com.akishimama.web.interceptor.ModelAndViewInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration =
                registry.addInterceptor(new ModelAndViewInterceptor()).addPathPatterns("/**");
        for(String pattern : WebSessionFilter.NO_SESSION_PATH_PREFIXES) {
            registration = registration.excludePathPatterns(pattern + "/**");
        }
    }
}

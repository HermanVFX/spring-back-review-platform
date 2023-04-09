package com.hermanvfx.springbackreviewplatform.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://192.168.0.150:3000/")
                .allowedOrigins("http://192.168.0.150:3001/")
                .allowedOrigins("http://192.168.0.150:8080/")
                .allowedOrigins("http://localhost:3000/");
    }
}

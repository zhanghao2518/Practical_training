package com.tju.practical.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .maxAge(3600)
                .allowCredentials(true)
                .allowedOrigins("*");
    }

}

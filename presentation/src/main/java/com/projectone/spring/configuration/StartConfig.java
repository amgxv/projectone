package com.projectone.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * projectone
 * com.projectone.spring.config
 * Created by : tech in 18/02/18.
 * Description :
 */

//

//@Configuration
public class StartConfig {
    @Bean
    // Configura CORS
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }

            @Bean
            public FreeMarkerViewResolver freemarkerViewResolver() {
                FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
                resolver.setCache(true);
                resolver.setPrefix("");
                resolver.setSuffix(".ftl");
                return resolver;
            }
        };
    }
}

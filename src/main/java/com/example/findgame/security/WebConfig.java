package com.example.findgame.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // If running your app from a different directory, consider using an absolute path
        registry.addResourceHandler("/uploaded-images/**")
                .addResourceLocations("file:./uploaded-images/"); // Adjusted to 'file:./uploaded-images/'
    }
}


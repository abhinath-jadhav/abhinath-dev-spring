// src/main/java/com/example/YourApplicationConfig.java
package com.akasa.food.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class YourApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS configuration to all endpoints
                .allowedOrigins("http://localhost:5173/") // Allow your front-end URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type") // Allow custom headers
                .allowCredentials(true); // Allow credentials (cookies, HTTP authentication)
    }
}

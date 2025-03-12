package com.rent.rentavehicle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://rent-a-vehicle-frontend-lemon.vercel.app/") // ✅ Allow frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // ✅ Ensure all necessary
                                                                                            // methods are allowed
                        .allowedHeaders("*") // ✅ Allow all headers
                        .exposedHeaders("*") // ✅ Allow access to exposed headers in frontend
                        .allowCredentials(true) // ✅ Important for auth headers like JWT
                        .maxAge(3600); // ✅ Cache the CORS settings for 1 hour
            }
        };
    }
}

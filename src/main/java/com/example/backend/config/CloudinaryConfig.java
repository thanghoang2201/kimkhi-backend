package com.example.backend.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dv8lrsi1b",
                "api_key", "386261864267133",
                "api_secret", "pVL5BJU3FusycdNa9mKaNWqF-Os"
        ));
    }
}

package com.example.beu2w2project.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
@Bean
    public Cloudinary cloudinaryUploader(@Value("${cloudinary.cloud_name}")String name,
                                         @Value("${cloudinary.api_key}")String apiKey,
                                         @Value("${cloudinary.api_secret}")String apiSecret){
    Map<String,String> config = new HashMap<>();
    config.put("cloud_name",name);
    config.put("api_key",apiKey);
    config.put("api_secret",apiSecret);
    return new Cloudinary(config);
}
}

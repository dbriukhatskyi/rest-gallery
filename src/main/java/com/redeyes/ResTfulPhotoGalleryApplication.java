package com.redeyes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class ResTfulPhotoGalleryApplication {
    public static final String IMAGES = "images";

    public static void main(String[] args) {
        SpringApplication.run(ResTfulPhotoGalleryApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(IMAGES).mkdir();
        };
    }
}

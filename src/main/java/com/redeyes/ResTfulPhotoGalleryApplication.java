package com.redeyes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * Entry point for Photo Gallery.
 *
 * @author Oleksandr Dres.
 * @author Dmytro Briukhatskyi.
 */
@SpringBootApplication
public class ResTfulPhotoGalleryApplication {
    /**
     * An entry point for application.
     *
     * @param args
     *        command-line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(ResTfulPhotoGalleryApplication.class, args);
    }
}

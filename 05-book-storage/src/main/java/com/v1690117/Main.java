package com.v1690117;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Application entry point
 */
@SpringBootApplication
@AllArgsConstructor
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

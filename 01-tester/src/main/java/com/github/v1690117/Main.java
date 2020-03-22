package com.github.v1690117;

import com.github.v1690117.app.Application;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Application entry point
 */

@SpringBootApplication
@AllArgsConstructor
public class Main implements CommandLineRunner {
    final private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Application app = appContext.getBean(Application.class);
        app.run();
    }

    @Bean
    public Locale locale() {
        return LocaleContextHolder.getLocale();
    }
}

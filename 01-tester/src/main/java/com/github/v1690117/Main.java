package com.github.v1690117;

import com.github.v1690117.app.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Application entry point
 */
@ComponentScan
@Configuration
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        Application app = ctx.getBean(Application.class);
        app.run();
    }
}

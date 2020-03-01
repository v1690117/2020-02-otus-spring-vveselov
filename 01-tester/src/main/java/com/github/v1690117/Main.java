package com.github.v1690117;

import com.github.v1690117.app.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application entry point
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        Application app = ctx.getBean(Application.class);
        app.run();
    }
}

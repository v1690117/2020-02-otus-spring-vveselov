package com.v1690117.app;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan({
        "com.v1690117.app"
})
public class Application {
    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations() {
        return Mockito.mock(NamedParameterJdbcOperations.class);
    }

    @Bean
    public EntityManager entityManager() {
        return Mockito.mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }
}

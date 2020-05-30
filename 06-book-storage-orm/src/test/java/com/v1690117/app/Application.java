package com.v1690117.app;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
@ComponentScan({
        "com.v1690117.app"
})
public class Application {
    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations() {
        return Mockito.mock(NamedParameterJdbcOperations.class);
    }
}

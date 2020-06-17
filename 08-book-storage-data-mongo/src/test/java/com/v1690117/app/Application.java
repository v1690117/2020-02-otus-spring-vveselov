package com.v1690117.app;

import com.mongodb.MongoClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({
        "com.v1690117.app.services"
})
@EnableMongoRepositories("com.v1690117.app.dao")
public class Application {
    @Bean
    public MongoClient mongoClient() {
        return Mockito.mock(MongoClient.class);
    }

    @Bean
    public MongoOperations mongoOperations() {
        return Mockito.mock(MongoOperations.class);
    }
}

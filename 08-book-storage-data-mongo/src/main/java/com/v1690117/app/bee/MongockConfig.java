package com.v1690117.app.bee;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import com.v1690117.app.bee.changelog.DatabaseChangelog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongockConfig {
    @Bean
    public Mongock mongock(MongoClient mongoClient) {
        return new SpringMongockBuilder(
                mongoClient,
                "books",
                DatabaseChangelog.class.getPackage().getName()
        ).build();
    }
}

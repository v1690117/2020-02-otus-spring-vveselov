package com.v1690117.app.bee;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.v1690117.app.bee.changelog.DatabaseChangelog;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class MongoBeeConfig {

    @Autowired
    private MongoClient mongo;

    @Bean
    public Mongobee mongobee(Environment environment) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName("books");
        runner.setChangeLogsScanPackage(DatabaseChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        return runner;
    }
}

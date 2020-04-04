package com.github.v1690117;

import com.github.v1690117.app.properties.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.github.v1690117.app.poll",
        "com.github.v1690117.app.util"
})
public class Application {
    @Bean
    public AppProperties properties() {
        AppProperties properties = new AppProperties();
        properties.setFilename("test-questions.csv");
        return properties;
    }
}

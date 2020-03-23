package com.github.v1690117;

import com.github.v1690117.app.properties.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Configuration
@ComponentScan({
        "com.github.v1690117.app.poll",
        "com.github.v1690117.app.util"
})
@Profile("test")
public class Application {

    // todo understand why poperties are not injected
    @Bean
    public AppProperties properties() {
        AppProperties properties = new AppProperties();
        properties.setFilename("test-questions.csv");
        return properties;
    }

    @Bean
    public Locale locale() {
        return LocaleContextHolder.getLocale();
    }
}

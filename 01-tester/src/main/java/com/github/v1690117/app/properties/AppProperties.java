package com.github.v1690117.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("application")
public class AppProperties {
    private String filename;
}

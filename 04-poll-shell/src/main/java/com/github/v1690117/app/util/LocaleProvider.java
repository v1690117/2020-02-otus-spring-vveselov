package com.github.v1690117.app.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Provides Locale instance for the app.
 */
@Component
public class LocaleProvider {
    @Bean
    public Locale locale() {
        return LocaleContextHolder.getLocale();
    }
}

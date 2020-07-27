package com.github.v1690117.app.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class LocalisedPrinter implements Printer {
    private final Locale locale;
    private final MessageSource messageSource;

    @Override
    public void print(String text, String... args) {
        System.out.println(
                messageSource.getMessage(
                        text,
                        args,
                        locale
                )
        );
    }
}

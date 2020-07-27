package com.github.v1690117.app.util;

import com.github.v1690117.test.fakes.FakeMessageSource;
import com.github.v1690117.test.util.NoEndLineText;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalisedPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    Printer printer;

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outContent));
        printer = new LocalisedPrinter(Locale.ENGLISH, new FakeMessageSource());
    }

    @DisplayName("Printer prints into out")
    @Test
    public void print() {
        String content = "Hello, World!";
        printer.print(content);
        assertEquals(
                new NoEndLineText(content),
                new NoEndLineText(outContent)
        );
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
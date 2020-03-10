package com.github.v1690117.app.util;

import com.github.v1690117.test.fakes.FakeMessageSource;
import com.github.v1690117.test.util.NoEndLineText;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class LocalisedPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    Printer printer;

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        printer = new LocalisedPrinter(Locale.ENGLISH, new FakeMessageSource());
    }

    @Test
    public void print() {
        String content = "Hello, World!";
        printer.print(content);
        Assert.assertEquals("Printer prints into out",
                new NoEndLineText(content),
                new NoEndLineText(outContent)
        );
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}
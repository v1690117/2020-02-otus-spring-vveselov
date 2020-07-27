package com.github.v1690117.app.util;

/**
 * Prints text somewhere.
 */
public interface Printer {
    /**
     * Prints provided text substituted with provided arguments.
     * For substitution the next syntax should be used:
     * "Hello, {0} {1}"
     * For this case two additional parametes must be provided.
     *
     * @param text text to be printed.
     * @param args list of substitution.
     */
    void print(String text, String... args);
}

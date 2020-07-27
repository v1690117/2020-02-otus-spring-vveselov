package com.github.v1690117.app.poll;

/**
 * Passes data into output and returns data retrieved from input.
 */
public interface IO {
    /**
     * Provides data to output and retrieves data from input.
     *
     * @param output data to be sent to output.
     * @return data retrieved from input.
     */
    Object readInputData(Object output);
}
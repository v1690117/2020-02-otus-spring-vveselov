package com.github.v1690117.app.poll.domain;

import lombok.AllArgsConstructor;

/**
 * Question that could be asked and validated.
 */
public interface Question {
    /**
     * Asks questions.
     */
    void ask();

    /**
     * Checks if provided answer is correct.
     *
     * @param givenAnswer answer to be validated.
     * @return true if answer is right and false if answer if wrong.
     */
    boolean validate(Answer givenAnswer);

    @AllArgsConstructor
    class FakeQuestion implements Question {
        private final boolean isCorrect;

        @Override
        public void ask() {

        }

        @Override
        public boolean validate(Answer givenAnswer) {
            return isCorrect;
        }
    }
}
package com.github.v1690117.app.poll.domain;

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

    class FakeQuestion implements Question {
        @Override
        public void ask() {

        }

        @Override
        public boolean validate(Answer givenAnswer) {
            return givenAnswer.isCorrect();
        }
    }
}
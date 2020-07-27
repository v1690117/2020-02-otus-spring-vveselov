package com.github.v1690117.app.poll.domain;

/**
 * Describes answer that could be right or wrong.
 */
public interface Answer {
    /**
     * Checks whether answer is right.
     *
     * @return true if answer is right and false otherwise.
     */
    boolean isCorrect();

    /**
     * Returns content of the answer.
     *
     * @return object describing content of answer.
     */
    Object value();

    class FakeRightAnswer implements Answer {
        @Override
        public boolean isCorrect() {
            return true;
        }

        @Override
        public Object value() {
            return "correct";
        }
    }

    class FakeWrongAnswer implements Answer {
        @Override
        public boolean isCorrect() {
            return false;
        }

        @Override
        public Object value() {
            return "incorrect";
        }
    }
}

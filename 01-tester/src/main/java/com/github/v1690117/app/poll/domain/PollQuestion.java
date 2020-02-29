package com.github.v1690117.app.poll.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PollQuestion implements Question {
    private final int number;
    private final String content;
    private final Variants variants;
    private final String correctAnswer;

    public PollQuestion(String... csvArgs) {
        this(
                Integer.valueOf(csvArgs[0]),
                csvArgs[1],
                new Variants(csvArgs[2]),
                csvArgs[3]
        );
    }

    @Override
    public boolean validate(Answer givenAnswer) {
        return this.correctAnswer.equals(givenAnswer.value());
    }

    @Override
    public void ask() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("%d) %s\n%s", number, content, variants);
    }

    @AllArgsConstructor
    private static class Variants {
        String content;

        @Override
        public String toString() {
            return content.replaceAll("\\|", "\n");
        }
    }
}

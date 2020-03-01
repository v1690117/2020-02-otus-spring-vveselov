package com.github.v1690117.app.poll.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PollAnswer implements Answer {
    private final Question question;
    private final String givenAnswer;

    @Override
    public boolean isCorrect() {
        return question.validate(this);
    }

    @Override
    public String value() {
        return givenAnswer;
    }
}

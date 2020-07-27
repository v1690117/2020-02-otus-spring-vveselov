package com.github.v1690117.app.poll.domain;

import lombok.Getter;

/**
 * Describes portion of statistic with total number of given answers
 * and number of correct answers.
 */
@Getter
public class StatsItem {
    private int questionsAsked;
    private int correctAnswers;

    /**
     * Increments number of correctly answered question.
     */
    public void addCorrect() {
        questionsAsked++;
        correctAnswers++;
    }

    /**
     * Increments number of wrong answers.
     */
    public void addWrong() {
        questionsAsked++;
    }

    /**
     * Calculates result of current item showing how successful answers are.
     * Bigger result means better quality of the answers.
     *
     * @return number presenting result.
     */
    public double getResult() {
        return questionsAsked == 0 ? 0 : (double) correctAnswers / questionsAsked;
    }
}
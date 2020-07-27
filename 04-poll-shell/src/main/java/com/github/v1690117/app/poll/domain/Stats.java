package com.github.v1690117.app.poll.domain;

/**
 * Receives statistics while application is running.
 */
public interface Stats {
    /**
     * Add new data to stats about user answers.
     *
     * @param user   - user of the app.
     * @param answer - answer provided by the user.
     */
    void add(User user, Answer answer);

    /**
     * Prints current stats.
     */
    void print();

    /**
     * Describes whether any statistic is available or it's just empty.
     *
     * @return true if there are no any stats.
     */
    boolean isEmpty();
}

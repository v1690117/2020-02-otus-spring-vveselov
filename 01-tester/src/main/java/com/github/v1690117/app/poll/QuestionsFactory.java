package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.Question;

import java.util.List;

/**
 * Creates list of Question objects.
 */
public interface QuestionsFactory {
    /**
     * Create list of Question objects.
     *
     * @return list of questions.
     */
    List<Question> questions();
}

package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.Question;

import java.util.LinkedList;
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

    class FakeQuestionsFactory implements QuestionsFactory {
        @Override
        public List<Question> questions() {
            List<Question> list = new LinkedList<>();
            list.add(new Question.FakeQuestion(true));
            list.add(new Question.FakeQuestion(false));
            return list;
        }
    }
}

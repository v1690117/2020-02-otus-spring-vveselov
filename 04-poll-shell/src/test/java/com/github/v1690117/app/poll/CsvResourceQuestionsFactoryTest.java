package com.github.v1690117.app.poll;

import com.github.v1690117.Application;
import com.github.v1690117.app.poll.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = Application.class)
class CsvResourceQuestionsFactoryTest {
    @Autowired
    private QuestionsFactory questionsFactory;
    private List<Question> questionList;

    @BeforeEach
    public void init() {
        questionList = questionsFactory.questions();
    }

    @DisplayName("Builds questions from provided file")
    @Test
    void questions() {
        assertFalse(questionList.isEmpty());
        assertEquals(3, questionList.size());
    }
}
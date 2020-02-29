package com.github.v1690117.app.poll;

import com.github.v1690117.app.Application;
import com.github.v1690117.app.poll.domain.Answer;
import com.github.v1690117.app.poll.domain.Question;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class SimplePoll implements Application {
    private final QuestionsFactory questionsFactory;
    private final IO io;
    private final List<Answer> answers;

    public SimplePoll(QuestionsFactory questionsFactory, IO io) {
        this(questionsFactory, io, new LinkedList<>());
    }

    @Override
    public void run() {
        List<Question> questions = questionsFactory.questions();
        printRules();
        for (Question question : questions) {
            Answer answer = Answer.class.cast(io.readInputData(question));
            answers.add(answer);
        }
        printResults();
    }

    private void printRules() {
        System.out.println("Hi, user! You will be asked some questions. Please type correct answer! Good luck!\n");
    }

    private void printResults() {
        System.out.println(
                String.format(
                        "Your result is %d of %d!\n",
                        answers.stream().filter(Answer::isCorrect).count(),
                        answers.size()
                )
        );
    }
}

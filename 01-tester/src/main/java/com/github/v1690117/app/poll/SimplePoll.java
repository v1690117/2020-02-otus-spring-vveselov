package com.github.v1690117.app.poll;

import com.github.v1690117.app.Application;
import com.github.v1690117.app.poll.domain.Answer;
import com.github.v1690117.app.poll.domain.Question;
import com.github.v1690117.app.util.Printer;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Service
public class SimplePoll implements Application {
    private final QuestionsFactory questionsFactory;
    private final IO io;
    private final Printer printer;
    private final List<Answer> answers;

    public SimplePoll(QuestionsFactory questionsFactory, IO io, Printer printer) {
        this.questionsFactory = questionsFactory;
        this.io = io;
        this.printer = printer;
        this.answers = new LinkedList<>();
    }

    @Override
    public void run() {
        List<Question> questions = questionsFactory.questions();
        greet();
        printRules();
        for (Question question : questions) {
            Answer answer = Answer.class.cast(io.readInputData(question));
            answers.add(answer);
        }
        printResults();
    }

    private void greet() {
        printer.print("askForFirstName");
        String firstName = new Scanner(System.in).next();
        printer.print("askForLastName");
        String lastName = new Scanner(System.in).next();
        printer.print("greeting", firstName, lastName);
    }

    private void printRules() {
        printer.print("rulesInfo");
    }

    private void printResults() {
        printer.print("resultInfo",
                String.valueOf(answers.stream().filter(Answer::isCorrect).count()),
                String.valueOf(answers.size())
        );
    }
}

package com.github.v1690117.app.poll;

import com.github.v1690117.app.Application;
import com.github.v1690117.app.poll.domain.Answer;
import com.github.v1690117.app.poll.domain.PollFinishedEvent;
import com.github.v1690117.app.poll.domain.Question;
import com.github.v1690117.app.poll.domain.Stats;
import com.github.v1690117.app.poll.domain.User;
import com.github.v1690117.app.util.Printer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Scanner;

@ShellComponent
public class SimplePoll implements Application {
    private final QuestionsFactory questionsFactory;
    private final IO io;
    private final Printer printer;
    private final Stats stats;
    private final ApplicationEventPublisher publisher;

    public SimplePoll(QuestionsFactory questionsFactory, IO io, Printer printer, Stats stats, ApplicationEventPublisher publisher) {
        this.questionsFactory = questionsFactory;
        this.io = io;
        this.printer = printer;
        this.stats = stats;
        this.publisher = publisher;
    }

    @ShellMethod(value = "Starts the poll", key = {"r", "run"})
    @Override
    public void run() {
        List<Question> questions = questionsFactory.questions();
        User user = getUser();
        greet(user);
        printRules();
        for (Question question : questions) {
            Answer answer = Answer.class.cast(io.readInputData(question));
            stats.add(user, answer);
        }
        publisher.publishEvent(new PollFinishedEvent(user));
    }

    private User getUser() {
        printer.print("askForFirstName");
        String firstName = new Scanner(System.in).next();
        printer.print("askForLastName");
        String lastName = new Scanner(System.in).next();
        User user = new User(firstName, lastName);
        return user;
    }

    private void greet(User user) {
        printer.print(
                "greeting",
                user.toString()
        );
    }

    private void printRules() {
        printer.print("rulesInfo");
    }

    @ShellMethod(value = "Prints statistic", key = {"s", "stats"})
    public void printStats() {
        stats.print();
    }
}

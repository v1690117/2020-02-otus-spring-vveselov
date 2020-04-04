package com.github.v1690117.app.poll.domain;

import com.github.v1690117.app.util.Printer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PollStats implements Stats {
    private final Printer printer;
    private final Map<User, StatsItem> statistic;
    private double bestResult;

    @Override
    public void add(User user, Answer answer) {
        StatsItem item = statistic.get(user);
        if (item == null) {
            item = new StatsItem();
            statistic.put(user, item);
        }
        if (answer.isCorrect()) {
            item.addCorrect();
        } else {
            item.addWrong();
        }
    }

    @Override
    public void print() {
        statistic.forEach((user, statsItem) ->
                printer.print(
                        "resultInfo",
                        user.toString(),
                        String.valueOf(statsItem.getCorrectAnswers()),
                        String.valueOf(statsItem.getQuestionsAsked())
                ));
    }

    @Override
    public boolean isEmpty() {
        return statistic.isEmpty();
    }

    @EventListener
    public void onPollFinished(PollFinishedEvent finishedEvent) {
        double userResult = statistic.get(
                finishedEvent.getUser()
        ).getResult();
        if (bestResult == 0) {
            bestResult = userResult;
        }
        if (userResult > bestResult) {
            printer.print("newBestResult");
        }
    }
}

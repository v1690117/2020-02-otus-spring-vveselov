package com.github.v1690117.app.poll;

import com.github.v1690117.app.Application;
import com.github.v1690117.app.poll.domain.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@RequiredArgsConstructor
@ShellComponent
public class PollCommands {
    private final Application poll;
    private final Stats stats;

    @ShellMethod(value = "Starts the poll", key = {"r", "run"})
    public void run() {
        poll.run();
    }

    @ShellMethod(value = "Prints statistic", key = {"s", "stats"})
    @ShellMethodAvailability(value = "isStatsAvailable")
    public void printStats() {
        stats.print();
    }

    public Availability isStatsAvailable() {
        return stats.isEmpty()
                ? Availability.unavailable("there is no statistic yet! Please run the poll first!")
                : Availability.available();
    }
}

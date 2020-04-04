package com.github.v1690117.app.poll.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Event publishing in the end of the polls.
 */
@Getter
public class PollFinishedEvent extends ApplicationEvent {
    private final User user;

    public PollFinishedEvent(Object source) {
        super(source);
        this.user = (User) source;
    }
}

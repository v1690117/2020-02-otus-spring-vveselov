package com.github.v1690117.app.poll.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Describes application user.
 */
@Data
@AllArgsConstructor
public class User {
    private final String firstName;
    private final String lastName;

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}

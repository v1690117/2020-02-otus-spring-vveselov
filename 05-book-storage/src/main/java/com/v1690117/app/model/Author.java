package com.v1690117.app.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;

    @Override
    public String toString() {
        return String.format(
                "%c. %s",
                firstName.charAt(0),
                lastName
        );
    }
}

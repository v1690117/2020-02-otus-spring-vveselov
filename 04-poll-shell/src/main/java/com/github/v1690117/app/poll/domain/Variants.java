package com.github.v1690117.app.poll.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Variants {
    private String content;

    @Override
    public String toString() {
        return content.replaceAll("\\|", "\n");
    }
}
package com.github.v1690117.test.util;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class NoEndLineText implements Text {
    String text;

    public NoEndLineText(Object object) {
        this(object.toString());
    }

    @Override
    public String content() {
        return text.replaceAll("\\n", "").replaceAll("\\r", "");
    }

    @Override
    public String toString() {
        return content();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoEndLineText that = (NoEndLineText) o;
        return content().equals(that.content());
    }

    @Override
    public int hashCode() {
        return Objects.hash(content());
    }
}

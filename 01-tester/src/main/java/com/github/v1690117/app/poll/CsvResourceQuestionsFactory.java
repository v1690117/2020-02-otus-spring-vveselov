package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.PollQuestion;
import com.github.v1690117.app.poll.domain.Question;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class CsvResourceQuestionsFactory implements QuestionsFactory {
    private final String filename;

    @SneakyThrows
    @Override
    public List<Question> questions() {
        return readLines()
                .map(line -> new PollQuestion(line.split(";")))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private Stream<String> readLines() {
        List<String> lines = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream(filename), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines.stream();
    }

    private File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        return new File(resource.getFile());
    }
}

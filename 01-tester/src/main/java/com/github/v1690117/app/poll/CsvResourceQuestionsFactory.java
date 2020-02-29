package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.PollQuestion;
import com.github.v1690117.app.poll.domain.Question;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class CsvResourceQuestionsFactory implements QuestionsFactory {
    private final String filename;

    @Override
    public List<Question> questions() {
        return readLines()
                .map(line -> new PollQuestion(line.split(";")))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private Stream<String> readLines() {
        File file = getFileFromResources();
        return Files.lines(file.toPath());
    }

    private File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filename);
        return new File(resource.getFile());
    }
}

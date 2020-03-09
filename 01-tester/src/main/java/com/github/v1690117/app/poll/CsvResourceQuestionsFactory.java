package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.PollQuestion;
import com.github.v1690117.app.poll.domain.Question;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@PropertySource("classpath:app.properties")
public class CsvResourceQuestionsFactory implements QuestionsFactory {
    private final String filename;

    public CsvResourceQuestionsFactory(@Value("${app.filename}") String filename) {
        this.filename = filename;
    }

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

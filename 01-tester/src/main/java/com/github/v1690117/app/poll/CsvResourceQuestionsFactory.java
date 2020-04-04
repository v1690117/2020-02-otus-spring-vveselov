package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.PollQuestion;
import com.github.v1690117.app.poll.domain.Question;
import com.github.v1690117.app.properties.AppProperties;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CsvResourceQuestionsFactory implements QuestionsFactory {
    private final Locale locale;
    private final AppProperties properties;

    @SneakyThrows
    @Override
    public List<Question> questions() {
        return readLines()
                .map(line -> new PollQuestion(line.split(";")))
                .collect(Collectors.toList());
    }

    private Stream<String> readLines() {
        List<String> lines = new LinkedList<>();
        String fileWithLocaleName = getFilename();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            this.getClass().getClassLoader().getResourceAsStream(fileWithLocaleName),
                            StandardCharsets.UTF_8
                    )
            );
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Problem with reading file %s!", fileWithLocaleName));
        }
        return lines.stream();
    }

    private String getFilename() {
        String fileWithLocaleName = properties.getFilename().replaceAll("^(.*)(\\.csv)$", "$1_" + locale.toString() + "$2");
        if (this.getClass().getClassLoader().getResourceAsStream(fileWithLocaleName) != null)
            return fileWithLocaleName;
        else
            return properties.getFilename();
    }
}

package com.github.v1690117.app.poll;

import com.github.v1690117.app.poll.domain.Answer;
import com.github.v1690117.app.poll.domain.PollAnswer;
import com.github.v1690117.app.poll.domain.Question;

import java.util.Scanner;

class PollConsoleIO implements IO {
    Scanner sc = new Scanner(System.in);

    @Override
    public Answer readInputData(Object output) {
        Question question = Question.class.cast(output);
        question.ask();
        Answer answer = new PollAnswer(question, sc.next());
        System.out.println();
        return answer;
    }
}
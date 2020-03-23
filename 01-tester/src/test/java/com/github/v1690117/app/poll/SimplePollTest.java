package com.github.v1690117.app.poll;

import com.github.v1690117.Application;
import com.github.v1690117.app.util.Printer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.ByteArrayInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

@SpringBootTest(classes = Application.class)
class SimplePollTest {
    @Autowired
    private SimplePoll poll;
    @MockBean
    private IO io;
    @MockBean
    private Printer printer;

    @DisplayName("Run without fail")
    @Test
    void run() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) {
                System.setIn(new ByteArrayInputStream("hello".getBytes()));
                return null;
            }
        }).when(printer).print(any());
        given(io.readInputData(any())).willReturn(new com.github.v1690117.app.poll.domain.Answer.FakeRightAnswer());
        poll.run();
    }
}
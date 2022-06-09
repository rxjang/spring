package com.example.persistence;

import com.example.domain.WebBoard;
import com.example.domain.WebReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebReplyRepositoryTest {

    @Autowired
    WebReplyRepository webReplyRepository;

    @Test
    void testInsertReplies() {
        Long[] arr = {301L, 300L, 299L};

        Arrays.stream(arr).forEach(num -> {

            WebBoard board = new WebBoard();
            board.setBno(num);

            IntStream.range(0, 10).forEach(i -> {
                WebReply reply = new WebReply();
                reply.setReplyText("Reply..." + i);
                reply.setReplyer("replyer" + i);
                reply.setBoard(board);

                webReplyRepository.save(reply);
            });

        });
    }

}
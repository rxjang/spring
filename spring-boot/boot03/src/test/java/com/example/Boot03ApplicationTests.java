package com.example;

import com.example.domain.Board;
import com.example.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Boot03ApplicationTests {

    @Autowired
    BoardRepository boardRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = Board.builder()
                    .title("No." + i)
                    .content(i + "의 내용 입니다")
                    .writer("user0" + (i % 10))
                    .build();
            boardRepository.save(board);
        }
    }

}

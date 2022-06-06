package com.example;

import com.example.domain.FreeBoard;
import com.example.domain.FreeBoardReply;
import com.example.persistence.FreeBoardReplyRepository;
import com.example.persistence.FreeBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Commit
public class FreeBoardTest {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardReplyRepository freeBoardReplyRepository;

    @Test
    void insertDummy() {
        IntStream.range(1, 200).forEach(i -> {
            FreeBoard board = FreeBoard.builder()
                    .title("자유 게시판 " + i)
                    .content(i + "의 내용")
                    .writer("user" + i % 10)
                    .build();
            freeBoardRepository.save(board);
        });
    }

    @Test
    @Transactional
    void insertReply2Way() {
        Optional<FreeBoard> result = freeBoardRepository.findById(199L);

        result.ifPresent(board -> {
            List<FreeBoardReply> replies = board.getReplies();

            FreeBoardReply reply = FreeBoardReply.builder()
                    .reply("댓글....")
                    .replyer("replyer00")
                    .board(board)
                    .build();
            replies.add(reply);

            board.setReplies(replies);

            freeBoardRepository.save(board);
        });
    }

    @Test
    void insertReply1Way() {
        FreeBoard board = FreeBoard.builder()
                .bno(199L)
                .build();

        FreeBoardReply reply = FreeBoardReply.builder()
                .replyer("댓글...")
                .replyer("replyer")
                .board(board)
                .build();

        freeBoardReplyRepository.save(reply);
    }

    @Test
    void findByBnoGreaterThanTest() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
            log.info(freeBoard.getBno() + ": " + freeBoard.getTitle());
        });
    }

    @Transactional
    @Test
    void findByBnoGreaterThanTest2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
            log.info(freeBoard.getBno() + ": " + freeBoard.getTitle() + "(" + freeBoard.getReplies().size() + ")");
        });
    }

    @Test
    void getPageTest() {

        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");

        freeBoardRepository.getPage(page)
                .forEach(arr -> log.info(Arrays.toString(arr)));

    }

}

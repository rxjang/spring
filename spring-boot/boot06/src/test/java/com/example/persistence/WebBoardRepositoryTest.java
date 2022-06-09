package com.example.persistence;

import com.example.domain.WebBoard;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@SpringBootTest
@Slf4j
@Commit
class WebBoardRepositoryTest {

    @Autowired
    WebBoardRepository webBoardRepository;

    @Test
    void insertBoardDummies() {
        IntStream.range(0, 300).forEach(i -> {
            WebBoard board = new WebBoard();
            board.setTitle("Sample Board Title " + i);
            board.setContent("Content Sample..." + i +" of Board");
            board.setWriter("user0" + (i % 10));

            webBoardRepository.save(board);
        });
    }

    @Test
    void testList1() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate(null, null), pageable);

        log.info("PAGE: {}",  result.getPageable());

        log.info("------------------------");

        result.getContent().forEach(webBoard -> log.info("{}", webBoard));
    }

    @Test
    void testList2() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate("t", "10"), pageable);

        log.info("PAGE: {}",  result.getPageable());

        log.info("------------------------");

        result.getContent().forEach(webBoard -> log.info("{}", webBoard));
    }

    @Test
    void getListWithQuery() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");

        List<Object[]> list = webBoardRepository.getListWithQuery(pageable);

        list.forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}
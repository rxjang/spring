package com.example.persistence;

import com.example.domain.Board;
import com.example.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void findBoardByTitle() {

        boardRepository.findBoardByTitle("No.177")
                .forEach(System.out::println);
    }

    @Test
    void findByWriterTest() {

        Collection<Board> results = boardRepository.findByWriter("user00");
        results.forEach(System.out::println);
    }

    @Test
    void findByWriterContainingTest() {

        boardRepository.findByWriterContaining("05")
                .forEach(System.out::println);
    }

    @Test
    void findByTitleContainingOrContentContainingTest() {

        boardRepository.findByTitleContainingOrContentContaining("17", "34")
                .forEach(System.out::println);
    }

    @Test
    void findByBnoGreaterThanOrderByBnoDescTest() {

        boardRepository.findByBnoGreaterThanOrderByBnoDesc(181L)
                .forEach(System.out::println);
    }

    @Test
    void findByBnoGreaterThanOrderByBnoDescPagingTest() {

        Pageable paging = PageRequest.of(0, 10);
        boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging)
                .forEach(System.out::println);
    }

    @Test
    void findByBnoGreaterThanTest() {

        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = boardRepository.findByBnoGreaterThan(0L, paging);

        System.out.println("Page size: " + result.getSize());
        System.out.println("Total pages: " + result.getTotalPages());
        System.out.println("Total count: " + result.getTotalElements());
        System.out.println("Next: " + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(System.out::println);
    }

    @Test
    void findByTitleTest() {
        boardRepository.findByTitle("20")
                .forEach(System.out::println);
    }

    @Test
    void findByContentTest() {
        boardRepository.findByContent("29")
                .forEach(System.out::println);
    }

    @Test
    void findByWriter2Test() {
        boardRepository.findByWriter2("56")
                .forEach(System.out::println);
    }

    @Test
    void findByTitle2Test() {
        boardRepository.findByTitle2("111")
                .forEach(b -> System.out.println(Arrays.toString(b)));
    }

    @Test
    void findByTitle3Test() {
        boardRepository.findByTitle3("89")
                .forEach(b -> System.out.println(Arrays.toString(b)));
    }

    @Test
    void findByPageTest() {
        Pageable pageable = PageRequest.of(0, 10);
        boardRepository.findByPage(pageable)
                .forEach(System.out::println);
    }

    @Test
    void testPredicate() {

        String type = "t";
        String keyword = "17";

        BooleanBuilder builder = new BooleanBuilder();

        QBoard board = QBoard.board;

        if (type.equals("t")) {
            builder.and(board.title.like("%" + keyword + "%"));
        }

        // bno > 0
        builder.and(board.bno.gt(0));

        Pageable pageable = PageRequest.of(0, 10);

        Page<Board> result = boardRepository.findAll(builder, pageable);

        System.out.println("Page size: " + result.getSize());
        System.out.println("Total pages: " + result.getTotalPages());
        System.out.println("Total count: " + result.getTotalElements());
        System.out.println("Next: " + result.nextPageable());

        List<Board> list = result.getContent();

        list.forEach(System.out::println);
    }


}
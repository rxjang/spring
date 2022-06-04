package com.example.persistence;

import com.example.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void inspect() {

        // 실제 객체의 클래스 이름
        Class<?> clz = boardRepository.getClass();

        System.out.println(clz.getName());
        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        // 클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();

        System.out.println(superClasses.getName());

    }

    @Test
    public void testInsert() {
        Board board = new Board();
        board.setTitle("게시물의 제목");
        board.setContent("내용.......");
        board.setWriter("user00");

        boardRepository.save(board);
    }

    @Test
    public void testReade() {
        boardRepository.findById(1L)
                .ifPresent(System.out::println);
    }

    @Test
    public void testUpdate() {
        System.out.println("Read first.....");
        Board board = boardRepository.findById(1L).get();

        System.out.println("Update Title..........");
        board.setTitle("제목 수정");

        System.out.println("Call save()........");
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {
        System.out.println("DELETE Entity");

        boardRepository.deleteById(1L);
    }
}
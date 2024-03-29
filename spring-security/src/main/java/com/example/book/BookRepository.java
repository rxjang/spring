package com.example.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where b.author.username = ?#{principal.username}")
    List<Book> findCurrentUserBooks();
}

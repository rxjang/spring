package com.example.book;

import com.example.account.Account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @ManyToOne
    private Account author;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Account getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }
}

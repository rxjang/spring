package com.example.common;

import com.example.account.Account;
import com.example.account.AccountService;
import com.example.book.Book;
import com.example.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account test1 = createUser("test1");
        Account test2 = createUser("test2");

        createBook("spring", test1);
        createBook("hibernate", test2);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }

    private Account createUser(String name) {
        Account account = new Account();
        account.setUsername(name);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}

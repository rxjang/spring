package com.security.demo.springsecurityform.common;

import com.security.demo.springsecurityform.account.Account;
import com.security.demo.springsecurityform.account.AccountService;
import com.security.demo.springsecurityform.book.Book;
import com.security.demo.springsecurityform.book.BookRepository;
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
        Account felix = createUSer("felix");
        Account felix2 = createUSer("felix2");
        createBook("spring", felix);
        createBook("hibernate", felix2);

    }

    private Account createUSer(String userame) {
        Account account = new Account();
        account.setUsername(userame);
        account.setPassword("123");
        account.setRole("USER");

        return accountService.createNew(account);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }
}

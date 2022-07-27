package com.example.form;

import com.example.account.Account;
import com.example.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceTest {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Test
    void dashboard() {
        Account account = new Account();
        account.setRole("USER");
        account.setUsername("test");
        account.setPassword("1234");
        accountService.createNew(account);

        UserDetails userDetails = accountService.loadUserByUsername("test");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "1234");
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        sampleService.dashboard();
    }
}
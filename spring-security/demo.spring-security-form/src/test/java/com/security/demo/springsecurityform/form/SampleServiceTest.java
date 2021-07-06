package com.security.demo.springsecurityform.form;

import com.security.demo.springsecurityform.account.Account;
import com.security.demo.springsecurityform.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SampleServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SampleService sampleService;

    @Test
    public void dashboard() {
        Account account = new Account();
        account.setRole("ADMIN");
        account.setUsername("Felix");
        account.setPassword("123");
        accountService.createNew(account);

        UserDetails userDetails = accountService.loadUserByUsername("Felix");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "123");
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        sampleService.dashboard();
    }

}
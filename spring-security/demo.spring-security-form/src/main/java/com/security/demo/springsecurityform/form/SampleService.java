package com.security.demo.springsecurityform.form;

import com.security.demo.springsecurityform.account.Account;
import com.security.demo.springsecurityform.account.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public void dashboard() {
        Account account = AccountContext.getAccount();
        System.out.println("================");
        System.out.println(account.getUsername());
    }
}

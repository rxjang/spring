package com.example.form;

import com.example.account.Account;
import com.example.account.AccountContext;
import com.example.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();   // 사용자 정보
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 권한
        Object credentials = authentication.getCredentials();
        boolean authenticated = authentication.isAuthenticated();

        Account account = AccountContext.getAccount();
        System.out.println("============" + account.getUsername());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(authentication);
        System.out.println("============= userDetails: " + userDetails.getUsername());

    }

    // 별도의 thread를 만들어 비동기로 처리해주는 어노테이션
    @Async
    public void asyncService() {
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called");

    }
}

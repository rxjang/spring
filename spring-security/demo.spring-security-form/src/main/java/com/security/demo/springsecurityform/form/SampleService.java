package com.security.demo.springsecurityform.form;

import com.security.demo.springsecurityform.common.SecurityLogger;
import org.springframework.context.annotation.Role;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

@Service
public class SampleService {

//    @Secured("ROLE_USER")
//    @RolesAllowed("ROLE_USER")
    @PreAuthorize("hasRole('ADMIN')")
//    @PostAuthorize()
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        System.out.println("================");
        System.out.println(authentication);
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        // @Async가 사용된 곳에는 security context 정보 공유 불가
        SecurityLogger.log("Async Service");
        System.out.println("Async service is called");
    }
}

package com.security.demo.springsecurityform.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInOutController {

    @GetMapping("/login")
    public String loginForm() {

        return "login";
    }

    @GetMapping("/logout")
    public String logout() {

        return "logout";
    }

}

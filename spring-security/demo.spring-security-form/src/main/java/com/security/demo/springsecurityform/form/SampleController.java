package com.security.demo.springsecurityform.form;

import com.security.demo.springsecurityform.account.Account;
import com.security.demo.springsecurityform.account.AccountRepository;
import com.security.demo.springsecurityform.account.UserAccount;
import com.security.demo.springsecurityform.common.CurrentUser;
import com.security.demo.springsecurityform.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello, " +
                    account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Spring Security Info");

        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello, " + principal.getName());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello admin, " + principal.getName());

        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello user, " + principal.getName());

        return "user";
    }

    @GetMapping("/async-handler")
    @ResponseBody
    public Callable<String> asyncHandler() {

        // Tomacat 이 할당해준 쓰레드
        SecurityLogger.log("MVC");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                    //callable 내의 쓰레드
                SecurityLogger.log("Callable");
                return "Async Handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {

        SecurityLogger.log("MVC, before async Service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async Service");

        return "Async Service";
    }
}

package com.example.form;

import com.example.account.Account;
import com.example.account.AccountContext;
import com.example.account.AccountRepository;
import com.example.account.UserAccount;
import com.example.common.CurrentUser;
import com.example.common.SecurityLogger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    private final SampleService sampleService;

    private final AccountRepository accountRepository;

    public SampleController(SampleService sampleService, AccountRepository accountRepository) {
        this.sampleService = sampleService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
//    public String index(Model model, Principal principal) { 이 principal은 spring security에서 가져오는게 아님
//    public String index(Model model, @AuthenticationPrincipal UserAccount userAccount) {
    public String index(Model model, @CurrentUser Account account) {

        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello " + account.getUsername());

        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        AccountContext.setAccount(accountRepository.findByUsername(principal.getName()));
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
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
        // request를 처리하고 있던 thread를 반환
        // 하던 일이 완료되면 응답을 반환

        SecurityLogger.log("MVC");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                // callable 안에서도 동일한 securityContext를 굥유 할 수 있음 => WebAsyncManagerIntegrationFilter
                SecurityLogger.log("Callable");
                return "Async Handler";
            }
        };
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {

        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }


}

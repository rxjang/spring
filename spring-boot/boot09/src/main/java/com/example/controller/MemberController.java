package com.example.controller;

import com.example.domain.Member;
import com.example.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final PasswordEncoder pwEncoder;

    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public void join() {

    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member) {
        log.info("Member: {}", member);

        String encryptPw = pwEncoder.encode(member.getUpw());

        log.info("en: {}", encryptPw);

        member.setUpw(encryptPw);

        memberRepository.save(member);

        return "/member/joinResult";
    }
}

package com.example;

import com.example.domain.Member;
import com.example.domain.MemberRole;
import com.example.persistence.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Commit
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testInsert() {
        for (int i = 0; i <= 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUname("사용자" + i);

            MemberRole memberRole = new MemberRole();
            if (i <= 80) {
                memberRole.setRoleName("BASIC");
            } else if (i <= 90) {
                memberRole.setRoleName("MANAGER");
            } else {
                memberRole.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(memberRole));

            memberRepository.save(member);
        }
    }

    @Test
    void testRead() {
        Optional<Member> result = memberRepository.findById("user85");

        result.ifPresent(member -> log.info("member {}", member));
    }
}

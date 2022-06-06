package com.example;

import com.example.domain.Member;
import com.example.domain.Profile;
import com.example.persistence.MemberRepository;
import com.example.persistence.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Commit
public class ProfileTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Test
    void testInsertMembers() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = Member.builder()
                    .uid("user" + i)
                    .uname("사용자" + i)
                    .upw("pw" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    void testInsertProfile() {
        Member member = new Member();
        member.setUid("user1");

        for (int i = 1; i < 5; i++) {
            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile1.setCurrent(true);
            }

            profile1.setMember(member);

            profileRepository.save(profile1);
        }
    }

    @Test
    void getMemberWithProfileCountTest() {

        List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");

        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    void getMemberWithProfileTest() {

        List<Object[]> result = memberRepository.getMemberWithProfile("user1");

        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}

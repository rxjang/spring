package org.zerock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.Member;
import org.zerock.domain.MemberRole;
import org.zerock.persistence.MemberRepository;

import lombok.extern.java.Log;

@SpringBootTest
@Log
@Commit
class MemberTests {
	
	@Autowired
	private MemberRepository repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void testInsert() {
		
		for(int i = 0; i<=100 ; i++) {
			
			Member member = new Member();
			member.setUid("user"+i);
			member.setUpw("pw"+i);
			member.setUname("사용자"+i);
			
			List<MemberRole> roles = new ArrayList<>();
			MemberRole role;
			
			if(i<=80) {
				role= new MemberRole();
				role.setRoleName("BASIC");
				roles.add(role);
			}else if(i<=90) {
				role= new MemberRole();
				role.setRoleName("MANAGER");
				roles.add(role);
			}else {
				role= new MemberRole();
				role.setRoleName("ADMIN");
				roles.add(role);
			}
			
			member.setRoles(roles);
			
			repo.save(member);
		}
	}
	
	@Test
	public void testRead() {
		
		Optional<Member> result = repo.findById("user85");
		
		result.ifPresent(member -> log.info("member" +member));
	}
	
	@Test
	public void testUpdateOlderMember() {
		
		List<String> ids = new ArrayList<>();
		
		for(int i = 0; i <= 100; i++) {
			
			ids.add("user"+i);
		}
		
		repo.findAllById(ids).forEach(member -> {
			
			member.setUpw(passwordEncoder.encode(member.getUpw()));
			repo.save(member);
		});
	}

}

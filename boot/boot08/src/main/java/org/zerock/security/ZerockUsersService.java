package org.zerock.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.persistence.MemberRepository;

import lombok.extern.java.Log;

@Log
@Service
public class ZerockUsersService implements UserDetailsService{
	
	@Autowired
	MemberRepository repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return 
				repo.findById(username)
				.filter(m -> m != null)
				.map(m -> new ZerockSecutiryUser(m)).get();

	}

}

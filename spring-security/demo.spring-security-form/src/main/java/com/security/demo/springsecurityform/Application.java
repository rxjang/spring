package com.security.demo.springsecurityform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	@Bean
	public PasswordEncoder passwordEncoder() {
		// sprint security 5 이전 버전
		// return NoOpPasswordEncoder.getInstance();

		// 다양한 passwordEncoding 을 지원 => 더 안전
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

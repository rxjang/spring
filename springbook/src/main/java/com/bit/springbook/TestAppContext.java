package com.bit.springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

import com.bit.springbook.user.service.DummyMailSender;

@Configuration
@Profile("test")
public class TestAppContext {
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
}

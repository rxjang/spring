package com.bit.springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.bit.springbook.user.service.DummyMailSender;

@Configuration
public class TestAppContext {
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
}

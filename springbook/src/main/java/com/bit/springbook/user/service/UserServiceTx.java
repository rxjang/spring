package com.bit.springbook.user.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserServiceTx implements UserService {

	@Setter
	UserService userService;
	//UserService를 구현한 다른 오브젝트를 DI 받는다.
	@Setter
	PlatformTransactionManager transactionManager;
	
	@Override
	public void add(User user) {
		userService.add(user);
	}
	
	@Override
	public void upgradeLevels() {
		TransactionStatus status=this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			userService.upgradeLevels();
			
			this.transactionManager.commit(status);
		}catch(RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}
	//DI받은 UserService 오브젝트에 모든 기능을 위임한다.
	
}

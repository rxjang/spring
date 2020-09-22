package com.bit.springbook.user.service;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserService{

	//트랜잭션 경계설정을 위한 추상 인터페이스
	@Setter
	private PlatformTransactionManager transactionManager;
	
	@Setter
	UserDao userDao;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER=50;
	public static final int MIN_RECOMMEND_FOR_GOLD=30;
	
	public void upgradeLevels(){
		TransactionStatus status=this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			List<User> users=userDao.getAll();
			for(User user:users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			this.transactionManager.commit(status);//정상적으로 마치면 트랜잭션 커밋
		}catch(Exception e) {
			this.transactionManager.rollback(status);//예외가 발생하면 롤백
			throw e;
		}
	}
	
	public boolean canUpgradeLevel(User user) {
		Level currentLevel=user.getLevel();
		switch(currentLevel) {
			case BASIC: return (user.getLogin()>=MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend()>=MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level: "+currentLevel);
		}
	}

	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	public void add(User user) {
		if(user.getLevel()==null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}
}

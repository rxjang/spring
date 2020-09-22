package com.bit.springbook.user.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserService{
	@Setter
	private DataSource dataSource;//connection을 생성할 때 사용할 DataSource를 DI받기로 한다
	
	@Setter
	UserDao userDao;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER=50;
	public static final int MIN_RECOMMEND_FOR_GOLD=30;
	
	public void upgradeLevels() throws Exception {
		TransactionSynchronizationManager.initSynchronization();
		//트랜잭션 동기화 관리자를 이용해 동기화 작업을 초기화 한다.
		Connection c=DataSourceUtils.getConnection(dataSource);//DB커넥션 생성과 동기화를 함께 해주는 유틸리티 메소드
		//DB커넥션을 생성하고 트랜잭션을 시작한다. 이후의 DAO작업은모두 여기서 시작한 트랜잭션 안에서 진행된다.
		c.setAutoCommit(false);
		try {
			List<User> users=userDao.getAll();
			for(User user:users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			c.commit();//정상적으로 마치면 트랜잭션 커밋
		}catch(Exception e) {
			c.rollback();//예외가 발생하면 롤백
			throw e;
		}finally {
			DataSourceUtils.releaseConnection(c, dataSource);
			//스츠링 유틸리티 메소드를 이용해 DB커넥션을 안전하게 닫는다
			TransactionSynchronizationManager.unbindResource(this.dataSource);
			TransactionSynchronizationManager.clearSynchronization();
			//동기화 작업 종료 및 정리
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

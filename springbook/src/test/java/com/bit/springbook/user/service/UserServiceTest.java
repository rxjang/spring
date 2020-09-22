package com.bit.springbook.user.service;

import static com.bit.springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static com.bit.springbook.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.dao.UserDaoJdbc;
import com.bit.springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDao dao;

	List<User> users;
	
	@Before
	public void setUp() {
		users=Arrays.asList(
				new User("bumjin","박범진","p1",Level.BASIC,MIN_LOGCOUNT_FOR_SILVER-1,0),
				new User("joytouch","강명성","p2",Level.BASIC,MIN_LOGCOUNT_FOR_SILVER,0),
				new User("erwins","신승한","p3",Level.SILVER,60,MIN_RECOMMEND_FOR_GOLD-1),
				new User("madnite1","이상호","p4",Level.SILVER,60,MIN_RECOMMEND_FOR_GOLD),
				new User("green","오민규","p5",Level.GOLD,100,Integer.MIN_VALUE)
			);
	}
	
	@Test
	public void bean() {
		assertThat(this.userService,is(notNullValue()));
	}
	
	@Test
	public void upgradeLevels() throws Exception {
		dao.deleteAll();
		
		for(User user:users)dao.add(user);
		
		userService.upgradeLevels();
		
		checkLevel(users.get(0), false);
		checkLevel(users.get(1), true);
		checkLevel(users.get(2), false);
		checkLevel(users.get(3), true);
		checkLevel(users.get(4), false);
	
	}
	
	public void checkLevel(User user,boolean upgraded) {
		User userUpdate=dao.get(user.getId());
		if(upgraded) {
			assertSame(userUpdate.getLevel(),user.getLevel().nextLevel());
		}else {
			assertSame(userUpdate.getLevel(),user.getLevel());
		}
	}
	
	@Test
	public void add() {
		dao.deleteAll();
		
		User userWithLevel =users.get(4);
		User userWithoutLevel=users.get(0);
		userWithLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead=dao.get(userWithLevel.getId());
		User userWithoutLevelRead=dao.get(userWithoutLevel.getId());
		
		assertSame(userWithLevelRead.getLevel(),userWithLevel.getLevel());
		assertSame(userWithoutLevelRead.getLevel(),Level.BASIC);
	}
	
	static class TestUserService extends UserService {
		private String id;
		
		private TestUserService(String id) {
			this.id=id;//예외를 발생시킬 User 오브젝트의 id를 지정할 수 있게 만듦
		}
		
		@Override
		protected void upgradeLevel(User user) {//UserService의 메소드를 오버라이드
			if(user.getId().equals(this.id))throw new TestUserServiceException();
			//지정된 id의 User 오브젝트가 발견되면 예외를 던져서 작업을 강제로 중단한다.
			super.upgradeLevel(user);
		}
	}
	
	static class TestUserServiceException extends RuntimeException{}

	@Test
	public void upgradeAllorNothing() throws Exception {
		UserService testUserService=new TestUserService(users.get(3).getId());
		testUserService.setUserDao(this.dao); //userDao를 수동 DI해준다
		testUserService.setDataSource(this.dataSource);
		dao.deleteAll();
		for(User user:users)dao.add(user);
		
		try {
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
			//TestUserService는 업그레이드 작업 중에 예외가 발생해야 함. 정상종료라면 문제가 있으니 실패
		}catch(TestUserServiceException e) {
			//TestUSserService가 던져주는 예외를 잡아서 계속 진행되도록 함. 그 외의 예외라면 테스트 실패
		}
		
		checkLevel(users.get(1), false);
		//예외가 발생하기 전에 레벨 변경이 있었던 사용자의 레벨이 처음 상태로 바뀌었나 확인
	}
}

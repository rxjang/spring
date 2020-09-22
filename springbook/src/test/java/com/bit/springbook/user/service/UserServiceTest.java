package com.bit.springbook.user.service;

import static com.bit.springbook.user.service.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static com.bit.springbook.user.service.UserService.MIN_RECOMMEND_FOR_GOLD;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

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
	UserDaoJdbc dao;

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
	public void upgraeLevels() {
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
}

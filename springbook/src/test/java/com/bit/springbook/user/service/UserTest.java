package com.bit.springbook.user.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.domain.User;

public class UserTest {
	User user;
	
	@Before
	public void setUp() throws Exception {
		user=new User();
	}

	@Test
	public void upgradeLevel() {
		Level[] levels=Level.values();
		for(Level level:levels) {
			if(level.nextLevel()==null) continue;
			user.setLevel(level);
			user.upgradeLevel();
			assertSame(user.getLevel(), level.nextLevel());
		}
	}

	@Test(expected=IllegalStateException.class)
	public void cannotUpgradeLevel() {
		Level[] levels=Level.values();
		for(Level level:levels) {
			if(level.nextLevel()==null) continue;
			user.setLevel(level);
			user.upgradeLevel();
		}
	}
}

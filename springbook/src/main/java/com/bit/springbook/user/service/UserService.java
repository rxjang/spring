package com.bit.springbook.user.service;

import com.bit.springbook.user.domain.User;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}

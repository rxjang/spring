package com.bit.springbook.user.dao;

import java.util.ArrayList;
import java.util.List;

import com.bit.springbook.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class MockUserDao implements UserDao {
	
	private List<User> users;//레벨 업그레이드 후보 User 오브젝트 목록 
	@Getter
	private List<User> updated=new ArrayList();//업그레이드 대상 오브젝트를 저장해둘 목록
	
	public MockUserDao(List<User> users) {
		this.users=users;
	}
	
	@Override
	public List<User> getAll() {
		return this.users;
	}//스텁 기능 제공
	
	@Override
	public void update(User user) {
		updated.add(user);
	}//목 오브젝트 기능 제공
	
	@Override
	public void add(User user) {throw new UnsupportedOperationException();}
	@Override
	public User get(String id) {throw new UnsupportedOperationException();}
	@Override
	public void deleteAll() {throw new UnsupportedOperationException();}
	@Override
	public int getCount() {throw new UnsupportedOperationException();}

}

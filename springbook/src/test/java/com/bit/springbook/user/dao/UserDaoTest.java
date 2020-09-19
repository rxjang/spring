package com.bit.springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)//스프링의 테스트 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = "/test-applicationContext.xml")
//@DirtiesContext//테스트 메소드에서 애플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 테스트 컨택스트 프레임워크에 알려준다.
public class UserDaoTest {
	@Autowired
	UserDao dao;
	
	private User user0;
	private User user1;
	private User user2;
	//setUp() 메소드에서 마느는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴수 변수로 선언
	
	@Before
	public void setUp() {
		this.user0=new User("ruixian0","seohyun0","dksdkffiwna");
		this.user1=new User("ruixian1","seohyun1","dksdkffiwna");
		this.user2=new User("ruixian2","seohyun2","dksdkffiwna");
		
//		dao=new UserDao();
//		DataSource dataSource=new SingleConnectionDataSource(
//				"jdbc:mysql://localhost/testdb?&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","spring","book",true);
//		dao.setDataSource(dataSource);
		
		System.out.println(this);
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		dao.add(user0);
		dao.add(user1);
		assertThat(dao.getCount(),is(2));
		
		User userget0=dao.get(user0.getId());
		assertThat(userget0.getName(), is(user0.getName()));
		assertThat(userget0.getPassword(), is(user0.getPassword()));
		
		User userget1=dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {
		
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		dao.add(user0);
		assertThat(dao.getCount(),is(1));
		
		dao.add(user1);
		assertThat(dao.getCount(),is(2));
		
		dao.add(user2);
		assertThat(dao.getCount(),is(3));
		
	}
	
//	@Test(expected=EmptyResultDataAccessException.class)
//	public void getUserFailure() throws SQLException, ClassNotFoundException {
//		dao.deleteAll();
//		assertThat(dao.getCount(),is(0));
//		
//		dao.get("unknow_id");
//	}
}

package com.bit.springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.springbook.TestApplicationContext;
import com.bit.springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)//스프링의 테스트 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(classes = TestApplicationContext.class)
public class UserDaoJdbcTest {
	@Autowired
	UserDaoJdbc dao;
	@Autowired DataSource dataSource;
	
	private User user0;
	private User user1;
	private User user2;
	//setUp() 메소드에서 마느는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴수 변수로 선언
	
	@Before
	public void setUp() {
		this.user0=new User("ruixian0","seohyun0","dksdkffiwna",Level.BASIC,1,0,"rxforp@naver.com");
		this.user1=new User("ruixian1","seohyun1","dksdkffiwna",Level.SILVER,55,10,"rxforp@naver.com");
		this.user2=new User("ruixian2","seohyun2","dksdkffiwna",Level.GOLD,100,40,"rxforp@naver.com");
		
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
		checkSameUser(userget0, user0);
		
		User userget1=dao.get(user1.getId());
		checkSameUser(userget1, user1);
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
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		dao.get("unknow_id");
	}
	
	@Test
	public void getAll() throws SQLException {
		dao.deleteAll();
		
		List<User> noUser=dao.getAll();
		assertThat(noUser.size(),is(0));
		
		dao.add(user0);
		List<User> users0=dao.getAll();
		assertThat(users0.size(),is(1));
		checkSameUser(user0,users0.get(0));

		dao.add(user1);
		List<User> users1=dao.getAll();
		assertThat(users1.size(),is(2));
		checkSameUser(user0,users1.get(0));
		checkSameUser(user1,users1.get(1));

		dao.add(user2);
		List<User> users2=dao.getAll();
		assertThat(users2.size(),is(3));
		checkSameUser(user0,users2.get(0));
		checkSameUser(user1,users2.get(1));
		checkSameUser(user2,users2.get(2));
	}

	@Test(expected=DataAccessException.class)
	public void duplicateKey() {
		dao.deleteAll();
		
		dao.add(user0);
		dao.add(user0);
	}
	
	@Test
	public void updte() {
		dao.deleteAll();
		
		dao.add(user0);
		dao.add(user1);
		
		user0.setName("서현");
		user0.setPassword("Wkdtlfna");
		user0.setLevel(Level.GOLD);
		user0.setLogin(1000);
		user0.setRecommend(999);
		dao.update(user0);
		
		User user0update=dao.get(user0.getId());
		checkSameUser(user0, user0update);
		User user1same=dao.get(user1.getId());
		checkSameUser(user1, user1same);
	}
	
//	@Test
//	public void sqlExceptionTranslate() {
//		dao.deleteAll();
//		
//		try {
//			dao.add(user0);
//			dao.add(user0);
//		}catch(DuplicateKeyException ex) {
//			SQLException sqlEx=(SQLException) ex.getRootCause();
//			SQLExceptionTranslator set= new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
//			assertSame(set.translate(null, null, sqlEx),DuplicateKeyException.class);
//		}
//	}//다른 오류 발생해버림 ㅎㅎㅎ
	
	public void checkSameUser(User user1,User user2) {
		assertThat(user1.getId(),is(user2.getId()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
		assertThat(user1.getLevel(),is(user2.getLevel()));
		assertThat(user1.getLogin(),is(user2.getLogin()));
		assertThat(user1.getRecommend(),is(user2.getRecommend()));
		
	}

}

package com.bit.springbook.user.service;

import static com.bit.springbook.user.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static com.bit.springbook.user.service.UserServiceImpl.MIN_RECOMMEND_FOR_GOLD;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.MockUserDao;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.dao.UserDaoJdbc;
import com.bit.springbook.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationContext.xml")
public class UserServiceTest {
	@Autowired UserService userService;
	@Autowired UserService testUserService;
	UserServiceImpl userServiceImpl;
//	@Autowired DataSource dataSource;
	@Autowired UserDaoJdbc dao;
	@Autowired PlatformTransactionManager transactionManager;
	@Autowired MailSender mailSender;
	@Autowired ApplicationContext context;	//펙토리 빈을 가져오려면 애플리케이션 컨텍스트가 필요하다
	
	List<User> users;
	
	@Before
	public void setUp() {
		users=Arrays.asList(
				new User("bumjin","박범진","p1",Level.BASIC,MIN_LOGCOUNT_FOR_SILVER-1,0,"rxforp@naver.com"),
				new User("joytouch","강명성","p2",Level.BASIC,MIN_LOGCOUNT_FOR_SILVER,0,"rxforp@naver.com"),
				new User("erwins","신승한","p3",Level.SILVER,60,MIN_RECOMMEND_FOR_GOLD-1,"rxforp@naver.com"),
				new User("madnite1","이상호","p4",Level.SILVER,60,MIN_RECOMMEND_FOR_GOLD,"rxforp@naver.com"),
				new User("green","오민규","p5",Level.GOLD,100,Integer.MIN_VALUE,"rxforp@naver.com")
			);
	}

	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception{
		UserServiceImpl userServiceImpl=new UserServiceImpl();
		//고립된 테스트에서는 테스트 대상 오브젝트를 직접 생성하면된다.

		MockUserDao mockUserDao=new MockUserDao(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		//목 오브젝트로 만든 UserDao를 직접 DI 해준다.
		
		MockMailSender mockMailSender=new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);
		//메일 발송 여부 확인을 위해 목 오브젝트 DI
		
		userServiceImpl.upgradeLevels();//테스트 대상 실행
		
		List<User> updated=mockUserDao.getUpdated();//MockUserDao로부터 업데이트 결과를 가져온다
		assertThat(updated.size(),is(2));
		checkUserAndLevel(updated.get(0),"joytouch",Level.SILVER);
		checkUserAndLevel(updated.get(1),"madnite1",Level.GOLD);
		
		List<String> request=mockMailSender.getRequests();
		assertThat(request.size(),is(2));
		assertThat(request.get(0),is(users.get(1).getEmail()));
		assertThat(request.get(1),is(users.get(3).getEmail()));
		//목오브젝트를 이용한 결과 확인
	}
	
	@Test
	public void mockUpgradeLevels() throws Exception{
		UserServiceImpl userServiceImpl=new UserServiceImpl();
		
		UserDao mockUserDao =mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		//다이내믹한 목 오브젝트 생성과 메소드의 리턴 값 설정, 그리고 DI까지 세줄이면 충분하다.
		
		MailSender mockMailSender=mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		//리턴 값이 없는 메소드를 가진 목 오브젝트는 더욱 간단하게 만들 수 있다.
		
		userServiceImpl.upgradeLevels();
		
		verify(mockUserDao,times(2)).update(any(User.class));
		//times 메소드 호출을 횟수를 검증해준다. any()를 사용하면 파라미터의 내용은 무시하고 호출 횟수만 확인할 수 있다.
		verify(mockUserDao,times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1));
		assertThat(users.get(1).getLevel(),is(Level.SILVER));
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(),is(Level.GOLD));
		//목 오브젝트가 제공하는 검증 기능을 통해서 어떤 메소드가 몇 번 호출됐는지, 파라미터는 무엇인지 확인할 수 있다.
		
		ArgumentCaptor<SimpleMailMessage> mailMessageArg=ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender,times(2)).send(mailMessageArg.capture());
		//파라미터를 정밀하게 검사하기 위해 캡처할수도있다.
		List<SimpleMailMessage> mailMessages=mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0],is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0],is(users.get(3).getEmail()));
	}
	
	public void checkUserAndLevel(User updated,String expectedId, Level expectedLevel) {
		assertThat(updated.getId(),is(expectedId));
		assertThat(updated.getLevel(),is(expectedLevel));
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
	
	static class TestUserServiceImpl extends UserServiceImpl {
		private String id="madnite1";
		
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
		dao.deleteAll();
		for(User user:users)dao.add(user);
		
		try {
			this.testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
			//트랜잭션 기능을 분리한 오브젲ㄱ트를 통해 예외 발생용 TestUserService가 호출되게 해야함
		}catch(TestUserServiceException e) {
			//TestUSserService가 던져주는 예외를 잡아서 계속 진행되도록 함. 그 외의 예외라면 테스트 실패
		}
		
		checkLevel(users.get(1), false);
		//예외가 발생하기 전에 레벨 변경이 있었던 사용자의 레벨이 처음 상태로 바뀌었나 확인
	}
	
	
	static class MockMailSender implements MailSender{
		private List<String> requests=new ArrayList<String>();
		
		public List<String> getRequests() {
			return requests;
		}
		
		@Override
		public void send(SimpleMailMessage mailMessage) throws MailException {
			requests.add(mailMessage.getTo()[0]);
			//전송 요청을 받은 이메일 주소를 저장해둔다(간단하게 첫번째 수신자 메일 주소만 저장)
		}

		@Override
		public void send(SimpleMailMessage[] mailMessage) throws MailException {
			
		}
		
	}
}

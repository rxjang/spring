package com.bit.springbook.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.domain.User;

import lombok.Setter;

@Component("userService")
public class UserServiceImpl implements UserService{

	//트랜잭션 경계설정을 위한 추상 인터페이스
	@Setter
	private PlatformTransactionManager transactionManager;
	
	@Setter
	@Autowired
	private MailSender mailSender;
	
	@Setter
	@Autowired
	UserDao userDao;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER=50;
	public static final int MIN_RECOMMEND_FOR_GOLD=30;
	
	@Override
	public void upgradeLevels(){
		List<User> users=userDao.getAll();
		for(User user:users) {
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
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
		sendUpgradeEMail(user);
	}
	
	private void sendUpgradeEMail(User user) {
			
			SimpleMailMessage mailMessage=new SimpleMailMessage();	
			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("useradmin@ksug.org");
			mailMessage.setSubject("Upgrade 안내");
			mailMessage.setText("사용자님의 등급이"+user.getLevel().name()+"로 업그레이드 되었습니다");
			//mailMessage 인터페이스의 구현 클래스 오브젝트를 만들어 메일 내용을 작성한다
			
			this.mailSender.send(mailMessage);

	}

	@Override
	public void add(User user) {
		if(user.getLevel()==null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}

	@Override
	public User get(String id) {return userDao.get(id); }

	@Override
	public List<User> getAll() {return userDao.getAll();}

	@Override
	public void update(User user) {userDao.update(user);}

	@Override
	public void deleteAll() {userDao.deleteAll();}

}

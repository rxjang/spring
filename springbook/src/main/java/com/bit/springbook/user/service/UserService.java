package com.bit.springbook.user.service;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.bit.springbook.user.dao.Level;
import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserService{

	//트랜잭션 경계설정을 위한 추상 인터페이스
	@Setter
	private PlatformTransactionManager transactionManager;
	
	@Setter
	private MailSender mailSender;
	
	@Setter
	UserDao userDao;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER=50;
	public static final int MIN_RECOMMEND_FOR_GOLD=30;
	
	public void upgradeLevels(){
		TransactionStatus status=this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			List<User> users=userDao.getAll();
			for(User user:users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			this.transactionManager.commit(status);//정상적으로 마치면 트랜잭션 커밋
		}catch(Exception e) {
			this.transactionManager.rollback(status);//예외가 발생하면 롤백
			throw e;
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
//			JavaMailSenderImpl mailSender=new JavaMailSenderImpl();	//mailSender구현 클래스의 오브젝트를 생성한다
//			mailSender.setHost("mail.server.com");
			
			SimpleMailMessage mailMessage=new SimpleMailMessage();	
			mailMessage.setTo(user.getEmail());
			mailMessage.setFrom("useradmin@ksug.org");
			mailMessage.setSubject("Upgrade 안내");
			mailMessage.setText("사용자님의 등급이"+user.getLevel().name()+"로 업그레이드 되었습니다");
			//mailMessage 인터페이스의 구현 클래스 오브젝트를 만들어 메일 내용을 작성한다
			
			this.mailSender.send(mailMessage);
		
//		Properties props=new Properties();
//		props.put("mail.smtp.host", "mail.ksug.org");
//		Session s=Session.getInstance(props,null);
//		
//		MimeMessage message=new MimeMessage(s);
//		
//		try {
//			message.setFrom(new InternetAddress("useradmin@ksug.org"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//			message.setSubject("Upgade 안내");
//			message.setText("사용자님의 등급이"+user.getLevel().name()+"로 업그레이드 되었습니다");
//			
//			Transport.send(message);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
	}

	public void add(User user) {
		if(user.getLevel()==null) user.setLevel(Level.BASIC);
		userDao.add(user);
	}
}

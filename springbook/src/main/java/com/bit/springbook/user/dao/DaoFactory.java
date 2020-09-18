package com.bit.springbook.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {//DI 컨테이너
	
	@Bean
	public UserDao userDao() {
		UserDao userDao=new UserDao();
//		userDao.setConnectionMaker(connectionMaker());
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/xe?&useSSL=false");
		dataSource.setUsername("scott");
		dataSource.setPassword("tiger");
		
		return dataSource;
	}
//	@Bean
//	public UserDao userDao() {
//		return new UserDao();
//	}
	
	@Bean
	public ConnectionMaker connectionMaker() {
		return new SimpleConnectionMaker();
	}
}

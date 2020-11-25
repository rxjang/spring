package com.bit.springbook;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.dao.UserDaoJdbc;
import com.bit.springbook.user.service.DummyMailSender;
import com.bit.springbook.user.service.UserService;
import com.bit.springbook.user.service.UserServiceImpl;
import com.bit.springbook.user.sqlService.OxmSqlService;
import com.bit.springbook.user.sqlService.SqlRegistry;
import com.bit.springbook.user.sqlService.SqlService;
import com.bit.springbook.user.sqlService.updatable.EmbeddedDbSqlRegistry;
import com.mysql.cj.jdbc.Driver;

@Configuration
@ImportResource("/test-applicationContext.xml")
@EnableTransactionManagement
public class TestApplicationContext {
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
		
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/testdb?&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm= new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDaoJdbc();
	}
	
	@Bean
	public UserService userService() {
		UserServiceImpl service=new UserServiceImpl();
		service.setUserDao(userDao());
		service.setMailSender(mailSender());
		return service;
	}

	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
	
	@Bean
	public SqlService sqlService() {
		OxmSqlService sqlService=new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		return sqlService;		
	}

	@Bean
	public SqlRegistry sqlRegistry() {
		EmbeddedDbSqlRegistry sqlRegistry=new EmbeddedDbSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}

	@Bean
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
				.setName("embeddedDatabase")
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript(
					"classpath:com/bit/springbook/user/sqlService/updatable/sqlRegistrySchema.sql")
				.build();
	}

	@Bean
	public Unmarshaller unmarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.bit.springbook.user.sqlService.jaxb");
		return marshaller;
	}

}

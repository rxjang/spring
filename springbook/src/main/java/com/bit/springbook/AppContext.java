package com.bit.springbook;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bit.springbook.AppContext.ProductionAppContext;
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
@ComponentScan(basePackages = "com.bit.springbook.user")
@Import({SqlServiceContext.class, TestAppContext.class, ProductionAppContext.class})
public class AppContext {
	@Autowired UserDao userDao;
	@Autowired UserService userService;
	
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
	
	@Configuration
	@Profile("production")
	public class ProductionAppContext {
		@Bean
		public MailSender mailSender() {
			JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
			mailSender.setHost("localhost");
			return mailSender;
		}
	}

}
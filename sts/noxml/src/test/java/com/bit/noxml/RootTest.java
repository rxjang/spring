package com.bit.noxml;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.noxml.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class RootTest {
	@Inject
	DataSource dataSource;
	
//	@Test
//	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
////		Class cl=Class.forName("com.bit.noxml.config.RootConfig");
//		Class cl=RootConfig.class;
//		RootConfig config=(RootConfig) cl.newInstance();
//		config.dataSource();
//	}
	
	@Test
	public void test() throws SQLException {
		assertThat(dataSource.getConnection(), is(Connection.class));
//		assertNotNull(dataSource.getConnection());
	}
	
}




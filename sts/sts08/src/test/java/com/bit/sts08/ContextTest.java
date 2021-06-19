package com.bit.sts08;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class ContextTest {
	Logger log=LoggerFactory.getLogger(ContextTest.class);
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void testDataSource() throws DataAccessException {
		assertSame(6,(jdbcTemplate.queryForList("select * from dept03").size()));
		jdbcTemplate.update("insert into dept03 (dname,loc) values ('test','test')");
		assertSame(6,(jdbcTemplate.queryForList("select * from dept03").size()));
	}

}

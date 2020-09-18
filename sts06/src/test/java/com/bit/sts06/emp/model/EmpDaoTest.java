package com.bit.sts06.emp.model;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class EmpDaoTest {
	@Autowired
	EmpDao empDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectAll() throws SQLException {
		assertNotNull(empDao.selectAll());
	}

	@Test
	public void testSelectAllInt() throws SQLException {
		assertSame(5,empDao.selectAll(1).size());
	}

	@Test
	public void testSelectOne() {
		fail("Not yet implemented");
	}

}

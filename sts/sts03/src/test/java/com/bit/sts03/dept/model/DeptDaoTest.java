package com.bit.sts03.dept.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bit.sts03.dept.model.entity.DeptVo;

/*
 @FixMethodOrder()
 괄호안의	 값들
 MethodSorters.DEFAULT: HashCode를 기반으로 순서가 결정되기 때문에 사용자가 예측하기 힘듭니다.
 MethodSorters.JVM: JVM에서 리턴되는 순으로 실행됩니다. 때에 따라서 실행시 변경됩니다.
 MethodSorters.NAME_ASCENDING: 메소드 명을 오름차순으로 정렬한 순서대로 실행됩니다.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeptDaoTest {
	static DeptVo target=new DeptVo(1,"test","test");
	DeptDao deptDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String sql="CREATE TABLE `bitcamp`.`dept03` (" + 
				"	`deptno` INT NOT NULL AUTO_INCREMENT," + 
				"	`dname` VARCHAR(10) NULL DEFAULT NULL," + 
				"	`loc` VARCHAR(10) NULL DEFAULT NULL," + 
				"	PRIMARY KEY (`deptno`)" + 
				")";
		ApplicationContext ac=null;
		ac=new ClassPathXmlApplicationContext("/applicationContext.xml");
		DataSource dataSource=(DataSource) ac.getBean("dataSource");
		Connection conn=dataSource.getConnection();
		conn.prepareStatement(sql).execute();
//		conn.prepareStatement("insert into dept03 (dname,loc) values ('"+target.getDname()+"','"+target.getLoc()+"')").executeUpdate();
		System.out.println("setUpBeforeClass()");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		String sql="DROP TABLE IF EXISTS `bitcamp`.`dept03`";
		ApplicationContext ac=null;
		ac=new ClassPathXmlApplicationContext("/applicationContext.xml");
		DataSource dataSource=(DataSource) ac.getBean("dataSource");
		Connection conn=dataSource.getConnection();
		conn.prepareStatement(sql).execute();
		System.out.println("teadDownAfterClass()");
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext ac=null;
		ac=new ClassPathXmlApplicationContext("/applicationContext.xml");
		deptDao=(DeptDao) ac.getBean("deptDao");
		System.out.println("setUp()");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown()");
	}

	@Test
	public void testSelectAll() throws SQLException {
//		assertNotNull(deptDao.selectAll()); //테이블에 데이터가 없어도 리스트를 반환하기 때문에 not null임
		assertSame(1, deptDao.selectAll().size());
		System.out.println("testSelectAll()");
	}
	@Test
	public void testSelectOne() throws SQLException {
		assertEquals(target, deptDao.selectOne(target.getDeptno()));
		System.out.println("testSelectOne");
	}
	
	@Test
	public void testInsertOne() {
		try {
			deptDao.insertOne(target);
			assertTrue(true);
		} catch (SQLException e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void testUpdateOne() throws SQLException {
		target.setDname("test1");
		target.setLoc("test2");
		assertSame(1,deptDao.updateOne(target));
		
	}
	
	@Test
	public void testZdeleteOne() throws SQLException {
		assertSame(1,deptDao.zDeleteOne(target.getDeptno()));
	}
	

}

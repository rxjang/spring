package com.bit.sts08.model;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bit.sts08.model.entity.DeptVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/applicationContext.xml"})
@Transactional
public class DeptDaoTest {
	Logger log=LoggerFactory.getLogger(DeptDaoTest.class);
	
	@Inject
	SqlSession sqlSession;
		
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSelectAll() {
		assertSame(6, sqlSession.getMapper(DeptDao.class).selectAll().size());
	}

	@Test
	public void testSelectOne() {
		assertNotNull(sqlSession.getMapper(DeptDao.class).selectOne(1));
	}

	@Test
	public void testInsertOne() {
		assertNotNull(sqlSession.getMapper(DeptDao.class).selectOne(1));
	}

	@Test
	public void testUpdateOne() {
	}

	@Test
	public void testDeleteOne() {
	}
	
	@Test
	public void testCRUD() throws DataAccessException {
		int tot=sqlSession.getMapper(DeptDao.class).selectAll().size();
		assertSame(tot, sqlSession.getMapper(DeptDao.class).selectSize());
		
		DeptVo target=new DeptVo(0,"test","test");
		sqlSession.getMapper(DeptDao.class).insertOne(target);
		assertSame(tot+1,sqlSession.getMapper(DeptDao.class).selectAll().size());
		
		target.setDeptno(sqlSession.getMapper(DeptDao.class).selectAll().get(0).getDeptno());
		target.setDname("Å×½ºÆ®");
		target.setLoc("Å×½ºÆ®Áö¿ª");
		log.debug(target.toString());
		sqlSession.getMapper(DeptDao.class).updateOne(target);
		assertEquals(target, sqlSession.getMapper(DeptDao.class).selectOne(target.getDeptno()));
		
		sqlSession.getMapper(DeptDao.class).deleteOne(target.getDeptno());
		assertSame(tot,sqlSession.getMapper(DeptDao.class).selectAll().size());
	}

}

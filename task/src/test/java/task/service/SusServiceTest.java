package task.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/applicationContext.xml")
@Transactional
public class SusServiceTest {

	@Autowired SqlSession sqlSession;
	@Autowired SusService susService;
	
	List<Sus_01Vo> sus_01List;
	List<Sus_02Vo> sus_02List;
	
	@Before
	public void setUp() {
		sus_01List=Arrays.asList(
				new Sus_01Vo("01a",1,"info-a",Date.valueOf("2020-11-29"),"a"),
				new Sus_01Vo("01b",1,"info-b",Date.valueOf("2020-11-29"),"b")
				);
	}
	
	@Test
	public void basicTest() {
		assertThat(this.sus_01List,is(notNullValue()));
	}
	
	@Test
	public void testgetAllSus_01() {
		susService.add(sus_01List.get(0));
		List<Sus_01Vo> list=susService.getAllSus_01();
		System.out.println(list.size());
		assertThat(list.get(0).getId(),is(1));
	}
	
	
}

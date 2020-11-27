package task.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import task.model.entity.Sus_01Vo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/applicationContext.xml")
public class SusServiceTest {

	@Autowired SqlSession sqlSession;
	
	Sus_01Vo sus_01Vo=new Sus_01Vo();
	
	@Autowired SusService susService;
	
	@Test
	public void basicTest() {
		assertThat(this.sus_01Vo,is(notNullValue()));
	}
	@Test
	public void testgetAllSus_01() {
		List<Sus_01Vo> list=susService.getAllSus_01();
		assertThat(list.size(),is(1));
	}
}

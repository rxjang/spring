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
	
	List<Sus_01Vo> sus_01SetUp;
	List<Sus_02Vo> sus_02SetUp;
	
	@Before
	public void setUp() {
		sus_01SetUp=Arrays.asList(
				new Sus_01Vo(1,"01a",1,"info-a",Date.valueOf("2020-11-29"),"a"),
				new Sus_01Vo(2,"01b",1,"info-b",Date.valueOf("2020-11-29"),"b")
				);
		sus_02SetUp=Arrays.asList(
				new Sus_02Vo(1,"02A",1,"info-A",Date.valueOf("2020-11-29"),"A"),
				new Sus_02Vo(2,"02B",1,"info-B",Date.valueOf("2020-11-29"),"B")
				);
	}
	
	/************************요구사항 1번 테스트*****************************/
	@Test
	public void testAdd() {
		assertThat(susService.getAllSus_01().size(),is(0));
		assertThat(susService.getAllSus_02().size(),is(0));
		//현재 점포에 상품이 없는것 확인
		susService.add(sus_01SetUp.get(0));
		//sus_01점포에 상품 등록
		List<Sus_01Vo> sus_01List=susService.getAllSus_01();
		List<Sus_02Vo> sus_02List=susService.getAllSus_02();
		assertThat(sus_01List.size(),is(1));
		assertThat(sus_02List.size(),is(1));
		//susService의 add함수 실행 후, sus_01과 sus_02의 상품 개수를 조회
		//sus_02의 상품개수 또한 1개로 업데이트 된 것을 알 수 있다
	}
	
	/************************요구사항 2번 테스트*****************************/
	@Test
	public void testUpdate() {
		susService.add(sus_01SetUp.get(0));
		assertThat(susService.getAllSus_01().get(0).getInfo(),is("info-a"));
		assertThat(susService.getAllSus_02().get(0).getInfo(),is("info-a"));
		//현재 정보 확인(업데이트 전)
		
		Sus_01Vo sus_01Vo=sus_01SetUp.get(0);
		sus_01Vo.setInfo("changed");
		susService.update(sus_01Vo);
		assertThat(susService.getAllSus_01().get(0).getInfo(),is("changed"));
		assertThat(susService.getAllSus_02().get(0).getInfo(),is("changed"));
		//업데이트 후 정보
		//update함수 실행 시, sus_01점포의 정보와 sus_02점포의 상품 정보 또한 바뀐 것을 확인 할 수 있다
		
	}
	
	
}

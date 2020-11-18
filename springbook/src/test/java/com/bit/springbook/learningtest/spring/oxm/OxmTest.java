package com.bit.springbook.learningtest.spring.oxm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bit.springbook.user.sqlService.jaxb.SqlType;
import com.bit.springbook.user.sqlService.jaxb.Sqlmap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/OxmTest-context.xml")
public class OxmTest {
	@Autowired Unmarshaller unmarshaller;
	//스프링 테스타가 테스트용 애플리케이션 컨텍스트에서 Unmarshaller 인터페이스 타입의 빈을 찾아서 테스트가 시작되기전에 이 변수를 넣어준다.
	
	@Test
	public void unmarshallSqlmap() throws XmlMappingException,IOException{
		Source xmlSource=new StreamSource(getClass().getResourceAsStream("sqlmap.xml"));
		Sqlmap sqlmap=(Sqlmap)this.unmarshaller.unmarshal(xmlSource);
		
		List<SqlType> sqlList=sqlmap.getSql();
		assertThat(sqlList.size(),is(3));
		assertThat(sqlList.get(0).getKey(),is("add"));
	}
}

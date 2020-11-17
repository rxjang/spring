package com.bit.springbook.learningtest.jdk.jaxb;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.bit.springbook.user.sqlService.jaxb.SqlType;
import com.bit.springbook.user.sqlService.jaxb.Sqlmap;

public class JaxbTest {
	
	@Test
	public void readSqlmap() throws JAXBException, IOException{
		String contextPath=Sqlmap.class.getPackage().getName();
		JAXBContext context=JAXBContext.newInstance(contextPath);
		//바인딩용 클래스들 위치를 가지고 JAXB 컨텍스트를 만든다.
		
		Unmarshaller unmarshaller = context.createUnmarshaller();	//언마살러 생성
		
		Sqlmap sqlmap=(Sqlmap) unmarshaller.unmarshal(getClass().getResourceAsStream("sqlmap.xml"));
		
		List<SqlType> sqlList=sqlmap.getSql();
		
		assertThat(sqlList.size(),is(3));
		assertThat(sqlList.get(0).getKey(),is("add"));
	}
}

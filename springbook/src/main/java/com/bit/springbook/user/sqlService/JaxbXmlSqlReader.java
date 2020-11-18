package com.bit.springbook.user.sqlService;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.sqlService.jaxb.SqlType;
import com.bit.springbook.user.sqlService.jaxb.Sqlmap;

import lombok.Setter;

public class JaxbXmlSqlReader implements SqlReader {
	private static final String DEFAULT_SQLMAP_FILE="sqlmap.xml";
	//굳이 상수로 만들지 않고 바로 sqlmapFile의 값으로 넣어도 상관없지만 이렇게 해주면 의도가 코드에 분명히 드러난다.
	
	@Setter
	private String sqlmapFile=DEFAULT_SQLMAP_FILE;
	//sqlMapFile은 SqlReader의 특정 구현 방법에 종속되는 프로퍼티가 된다.
	
	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath=Sqlmap.class.getPackage().getName();
		try {
			JAXBContext context=JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller=context.createUnmarshaller();
			InputStream is=UserDao.class.getResourceAsStream(sqlmapFile);
			Sqlmap sqlmap=(Sqlmap)unmarshaller.unmarshal(is);
			for(SqlType sql:sqlmap.getSql()) {
				sqlRegistry.registerSql(sql.getKey(), sql.getValue());
				//SQL 저장 로직 구현에 독립적인 인터페이스 메소드를 통해 읽어들인 SQL과 키를 전달한다.
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

}

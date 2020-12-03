package com.bit.springbook.user.sqlService;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import com.bit.springbook.user.dao.UserDao;
import com.bit.springbook.user.sqlService.jaxb.SqlType;
import com.bit.springbook.user.sqlService.jaxb.Sqlmap;

import lombok.Setter;

public class OxmSqlService implements SqlService {
	private final OxmSqlReader oxmSqlReader=new OxmSqlReader();
	//final이므로 변경불가능하다. OxmSqlService와 OxmSqlReader은 강하게 결합돼서 하나의 빈으로 동륵되고 한 번에 설정할 수 있다.
	private final BaseSqlService baseSqlService=new BaseSqlService();
	//SqlService의 실제 구현 부분을 위임할 대상인 BaseSqlService를 인스턴스 변수로 정의해둔다.
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.oxmSqlReader.setUnmarshaller(unmarshaller);
	}
	
	public void setSqlmap(Resource sqlmap) {
		this.oxmSqlReader.setSqlmap(sqlmap);
	}
	
	@Setter
	private SqlRegistry sqlRegistry=new HashMapSqlRegistry();
	//oxmSqlReader와 달리 단지 디폴트 오브젝트로 만들어진 프롶티다. 따라서 필요에 따라 다른 DI를 통해 교체 가능하다.

	@PostConstruct
	public void loadSql() {
		this.baseSqlService.setSqlReader(this.oxmSqlReader);
		this.baseSqlService.setSqlRegistry(this.sqlRegistry);
		//OxmSqlService의 프로퍼티를 통해서 초기화된 SQlReqder와 SqlRegistry를 실제 작업을 위임할 대상인 baseSqlService에 주입한다.
		
		this.baseSqlService.loadSql();
		//SQL을 등록하는 초기화 작업을 baseSqlService에 위임한다. 
	}

	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		return this.baseSqlService.getSql(key);
		//SQL을 찾아오는 작업도 baseSqlService에 위임한다.
	}
	
	private class OxmSqlReader implements SqlReader{
		//private 멤버 클래스로 정의한다. 톱레벨 클래스인 OxmSqlService만이 사용할 수 있다.
		@Setter
		private Resource sqlmap=new ClassPathResource("/sqlmap.xml");
		@Setter
		private Unmarshaller unmarshaller;
		
		@Override
		public void read(SqlRegistry sqlRegistry) {
			try {
				Source source = new StreamSource(
						sqlmap.getInputStream());
				Sqlmap sqlmap=(Sqlmap)this.unmarshaller.unmarshal(source);
				for(SqlType sql:sqlmap.getSql()) {
					sqlRegistry.registerSql(sql.getKey(), sql.getValue());
				}
			}catch(IOException e) {
				throw new IllegalArgumentException(this.sqlmap+"을 가올 수 없습니다",e);
			}
		}
		
	}

}

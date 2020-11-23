package com.bit.springbook.user.sqlService.updatable;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.bit.springbook.user.sqlService.SqlUpdateFailureException;
import com.bit.springbook.user.sqlService.UpdatableSqlRegistry;

public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest{
	EmbeddedDatabase db;
	
	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		db=new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL).addScript("classpath:/com/bit/springbook/user/sqlService/updatable/sqlRegistrySchema.sql").build();
		
		EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
		embeddedDbSqlRegistry.setDataSource(db);
		
		return embeddedDbSqlRegistry;
	}

	@After
	public void tearDown() throws Exception {
		db.shutdown();
	}
	
	@Test
	public void transactionUpdate() {
		checkFind("SQL1","SQL2","SQL3");
		//초기상태를 확인한다.
		//이미 슈퍼클래스의 다른 테스트메소드에서 확인하긴했지만 트랜잭션 롤백 후의 결과와 비교돼서 이 테스트의 목적인 롤백 후의 상태는 처음과
		//동일하다는 것을 비교해서 보여주기위해 넣었다.
		
		Map<String, String> sqlmap=new HashMap<String,String>();
		sqlmap.put("KEY1","Modified1");
		sqlmap.put("KEY9999!@#$","Modified9999");
		
		try {
			sqlRegistry.updateSql(sqlmap);
			fail();
			//예외가 발생해서 catch블록으로 넘아가지않으면 뭔가 잘못된 것이다.
			//그때는 테스트를 강제로 실패하게 만들고 기대와 다르게 동작한 원인을 찾도록 해야한다.
		}catch(SqlUpdateFailureException e) {}
	
		checkFind("SQL1","SQL2","SQL3");
	}


}

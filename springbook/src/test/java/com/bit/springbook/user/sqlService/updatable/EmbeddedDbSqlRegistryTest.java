package com.bit.springbook.user.sqlService.updatable;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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


}

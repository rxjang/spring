package com.bit.springbook;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.bit.springbook.user.sqlService.OxmSqlService;
import com.bit.springbook.user.sqlService.SqlRegistry;
import com.bit.springbook.user.sqlService.SqlService;
import com.bit.springbook.user.sqlService.updatable.EmbeddedDbSqlRegistry;

@Configuration
public class SqlServiceContext {
	@Bean
	public SqlService sqlService() {
		OxmSqlService sqlService=new OxmSqlService();
		sqlService.setUnmarshaller(unmarshaller());
		sqlService.setSqlRegistry(sqlRegistry());
		return sqlService;		
	}

	@Bean
	public SqlRegistry sqlRegistry() {
		EmbeddedDbSqlRegistry sqlRegistry=new EmbeddedDbSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}

	@Bean
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
				.setName("embeddedDatabase")
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript(
					"classpath:com/bit/springbook/user/sqlService/updatable/sqlRegistrySchema.sql")
				.build();
	}

	@Bean
	public Unmarshaller unmarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.bit.springbook.user.sqlService.jaxb");
		return marshaller;
	}

}

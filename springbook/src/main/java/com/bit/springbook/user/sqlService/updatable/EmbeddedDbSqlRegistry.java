package com.bit.springbook.user.sqlService.updatable;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.bit.springbook.user.sqlService.SqlNotFoundException;
import com.bit.springbook.user.sqlService.SqlUpdateFailureException;
import com.bit.springbook.user.sqlService.UpdatableSqlRegistry;

public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry {
	SimpleJdbcTemplate jdbc;
	TransactionTemplate transactionTemplate;
	//JdbcTemplate과 트랜잭션을 동기화해주는 트랜잭션 템플릿이다.
	//멀티스레드 환경에서 공유 가능하다.

	public void setDataSource(DataSource dataSource) {
		jdbc=new SimpleJdbcTemplate(dataSource);
		transactionTemplate=new TransactionTemplate(new DataSourceTransactionManager(dataSource));
		//dataSource로 TransactionManager을 만들고 이를 이용해 TransactionTemplate을 생성한다.
		transactionTemplate.setIsolationLevel(TransactionTemplate.ISOLATION_READ_COMMITTED);
	}
	
	@Override
	public void registerSql(String key, String sql) {
		jdbc.update("insert into sqlmap(key_,sql_) values (?,?) ", key , sql);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		try {
			return jdbc.queryForObject("select sql_ from sqlmap where key_=?", String.class, key);
		}catch(EmptyResultDataAccessException e) {
			throw new SqlNotFoundException(key+"에 해당하는 SQL을 찾을수 없습니다",e);
		}
	}

	@Override
	public void updateSql(String key, String sql) throws SqlUpdateFailureException {
		int affected=jdbc.update("update sqlmap set sql_=? where key_=?", sql, key);
		if(affected==0) {
			throw new SqlNotFoundException(key+"에 해당하는 SQL을 찾을 수 없습니다");
		}
	}

	@Override
	public void updateSql(final Map<String, String> sqlmap) throws SqlUpdateFailureException {
		//익명 내부 클래스로 만들어지는 콜백 오브젝트 안에서 사용되는 것이라 final로 선언해줘야 한다.
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				for(Map.Entry<String, String> entry :sqlmap.entrySet()) {
					updateSql(entry.getKey(),entry.getValue());
				}
			}
		});
		//트랜잭션 템플릿이 만드는 트랜잭션 경계 안에서 동작할 코드를 콜백 형태로 만들고 TransactionTemplate의 execute()메소드에 전달한다.
	}

}

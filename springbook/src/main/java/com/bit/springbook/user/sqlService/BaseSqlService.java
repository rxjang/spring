package com.bit.springbook.user.sqlService;

import javax.annotation.PostConstruct;

import lombok.Setter;

public class BaseSqlService implements SqlService {
	@Setter
	protected SqlReader sqlReader;
	@Setter
	protected SqlRegistry sqlRegistry;
	//BaseSqlService는 상속을 통해 확장해서 사용하게에 적합하다. 서브클래스에서 필요한 경우 접근할 수 있도록 protected로 선언한다.
	
	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(this.sqlRegistry);
	}
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		try {
			return this.sqlRegistry.findSql(key);
		}catch(SqlNotFoundException e) {
			throw new SqlRetrievalFailureException(e);
		}
	}

}

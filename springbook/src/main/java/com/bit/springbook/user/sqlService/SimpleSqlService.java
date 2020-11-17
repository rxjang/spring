package com.bit.springbook.user.sqlService;

import java.util.Map;

import lombok.Setter;

public class SimpleSqlService implements SqlService {
	@Setter
	private Map<String,String> sqlMap;
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		String sql=sqlMap.get(key);	//내부 SqlMap에서 SQL을 가져온다. 
		if(sql==null) throw new SqlRetrievalFailureException(key+"에 대한 SQL을 찾을 수 없습니다.");
		//인터페이스에 정의된 규약대로 SQL을 가져오는 데 실패하면 예외를 던지게한다. 
		else
			return sql;
	}

}

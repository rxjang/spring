package com.bit.springbook.user.sqlService;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}

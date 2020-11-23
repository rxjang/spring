package com.bit.springbook.user.sqlService.updatable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bit.springbook.user.sqlService.SqlNotFoundException;
import com.bit.springbook.user.sqlService.UpdatableSqlRegistry;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
	UpdatableSqlRegistry sqlRegistry;

	
	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		return new ConcurrentHashMapSqlRegistry();
	}

}

package com.bit.springbook.user.sqlService.updatable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bit.springbook.user.sqlService.SqlNotFoundException;
import com.bit.springbook.user.sqlService.UpdatableSqlRegistry;

public abstract class AbstractUpdatableSqlRegistryTest {
	UpdatableSqlRegistry sqlRegistry;

	@Before
	public void setUp() throws Exception {
		sqlRegistry= createUpdatableSqlRegistry();
		sqlRegistry.registerSql("KEY1", "SQL1");
		sqlRegistry.registerSql("KEY2", "SQL2");
		sqlRegistry.registerSql("KEY3", "SQL3");
	}

	abstract protected UpdatableSqlRegistry createUpdatableSqlRegistry();
	
	@Test
	public void find() {
		checkFindResult("SQL1","SQL2","SQL3");
	}
	
	protected void checkFindResult(String expected1, String expected2, String expected3) {
		assertThat(sqlRegistry.findSql("KEY1"), is(expected1));
		assertThat(sqlRegistry.findSql("KEY2"), is(expected2));
		assertThat(sqlRegistry.findSql("KEY3"), is(expected3));
	}
	
	@Test(expected=SqlNotFoundException.class)
	public void unknownKey() {
		sqlRegistry.findSql("SQL311");
	}
	
	@Test
	public void updateSingle() {
		sqlRegistry.updateSql("KEY2", "Modified2");
		checkFindResult("SQL1", "Modified2", "SQL3");
	}

}

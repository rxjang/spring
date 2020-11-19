package com.bit.springbook.user.sqlService;

public class OxmSqlService implements SqlService {
	private final OxmSqlReader oxmSqlReader=new OxmSqlReader();
	//final이므로 변경불가능하다. OxmSqlService와 OxmSqlReader은 강하게 결합돼서 하나의 빈으로 동륵되고 한 번에 설정할 수 있다.
	
	
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class OxmSqlReader implements SqlReader{

		@Override
		public void read(SqlRegistry sqlRegistry) {
			// TODO Auto-generated method stub
			
		}
		
	}

}

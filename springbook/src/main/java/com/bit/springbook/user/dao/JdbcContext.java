package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.Setter;

public class JdbcContext {
	@Setter
	private DataSource dataSource;
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=this.dataSource.getConnection();
			pstmt=stmt.makePreparedStatement(conn);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}finally {
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try{conn.close();}catch(SQLException e) {}
		}
	}
}

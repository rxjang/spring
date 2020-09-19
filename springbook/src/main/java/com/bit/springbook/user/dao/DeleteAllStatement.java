package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import lombok.Setter;

public class DeleteAllStatement implements StatementStrategy {

	@Override
	public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
		PreparedStatement pstmt=conn.prepareStatement("delete from users");
		return pstmt;
	}
	
	

}

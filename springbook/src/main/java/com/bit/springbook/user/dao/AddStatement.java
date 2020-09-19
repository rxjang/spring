package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit.springbook.user.domain.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddStatement implements StatementStrategy{
	User user;
	
	@Override
	public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
		PreparedStatement pstmt=conn.prepareStatement("insert into users(id,name,password) values(?,?,?)");
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		return pstmt;
	}
}

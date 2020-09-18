package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker{
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/xe?&useSSL=false";
	String identity="scott";
	String pw="tiger";
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(url, identity, pw);
		return conn;
	}
}

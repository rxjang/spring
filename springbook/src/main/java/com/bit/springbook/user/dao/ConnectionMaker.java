package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {//스프링 빈
	public Connection makeConnection()  throws ClassNotFoundException, SQLException;
}

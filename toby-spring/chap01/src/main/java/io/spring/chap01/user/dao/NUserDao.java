package io.spring.chap01.user.dao;

import io.spring.chap01.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class NUserDao extends UserDao {
public class NUserDao {

//    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook?useSSL=false&allowPublicKeyRetrieval=true",
                "spring",
                "Springbook@123");
        return c;
    }

}

package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserDao {//스프링 빈
	
	@Setter
	private DataSource dataSource;
	
	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection conn=dataSource.getConnection();
		String sql="insert into users(id,name,password) values (?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	public User get(String id) throws SQLException, ClassNotFoundException {
		String sql="select * from users where id=?";
		Connection conn=dataSource.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		User user=null;
		if(rs.next()) {
			user=new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		if(user==null) throw new EmptyResultDataAccessException(1);
		return user;
	}
	
	public void deleteAll() throws SQLException{
		Connection conn=dataSource.getConnection();
		PreparedStatement pstmt=conn.prepareStatement("delete from users");
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	
	public int getCount() throws SQLException{
		Connection conn=dataSource.getConnection();
		PreparedStatement pstmt=conn.prepareStatement("select count(*) from users");
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int count=rs.getInt(1);
		rs.close();
		pstmt.close();
		conn.close();
		
		return count;
	}
}

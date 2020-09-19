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
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	//새터이면서 JdbcCotnext에 대한 생성,DI작업을 동시에 수행한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext=new JdbcContext();//의존 오브젝트 주
		this.jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;//아직 JdbcContext를 적용하지 않은 메소드를 위해 저장해준다
	}
	
	public void add(final User user) throws SQLException{
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt=conn.prepareStatement("insert into users(id,name,password) values(?,?,?)");
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getPassword());
				return pstmt;
			}
		});
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
		this.jdbcContextWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt=conn.prepareStatement("delete from users");
				return pstmt;
			}
		});
	}
	
	public int getCount() throws SQLException{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			pstmt=conn.prepareStatement("select count(*) from users");
			rs=pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch(SQLException e) {
			throw e;
		}finally {
			if(rs!=null)try{rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try{conn.close();}catch(SQLException e) {}
		}
	}
	

	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
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

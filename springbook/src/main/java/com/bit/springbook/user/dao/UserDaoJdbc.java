package com.bit.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.bit.springbook.Exceptions.DuplicateUserIdException;
import com.bit.springbook.user.domain.User;
import com.mysql.cj.exceptions.MysqlErrorNumbers;

import lombok.Setter;

public class UserDaoJdbc implements UserDao{//스프링 빈
	private JdbcTemplate jdbcTemplate;
	
	//새터이면서 JdbcCotnext에 대한 생성,DI작업을 동시에 수행한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id=?", new Object[] {id},this.userMapper);
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}
	
	public void add(final User user) throws DuplicateKeyException{
		this.jdbcTemplate.update("insert into users(id, name, password) values (?,?,?)", user.getId(),user.getName(),user.getPassword());
//		try {
//			this.jdbcTemplate.update("insert into users(id, name, password) values (?,?,?)", user.getId(),user.getName(),user.getPassword());
//		}catch(SQLException e) {
//			if(e.getErrorCode()==MysqlErrorNumbers.ER_DUP_ENTRY)
//				 throw new DuplicateUserIdException(e);
//			else throw new RuntimeException(e);
//		}
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from users");
	}
	
	private RowMapper<User> userMapper=new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user=new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};
//	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		try {
//			conn=dataSource.getConnection();
//			pstmt=stmt.makePreparedStatement(conn);
//			pstmt.executeUpdate();
//		}catch(SQLException e) {
//			throw e;
//		}finally {
//			if(pstmt!=null)try{pstmt.close();}catch(SQLException e) {}
//			if(conn!=null)try{conn.close();}catch(SQLException e) {}
//		}
//	}
}

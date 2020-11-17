package com.bit.springbook.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bit.springbook.user.domain.User;

import lombok.Setter;

public class UserDaoJdbc implements UserDao{//스프링 빈
	private JdbcTemplate jdbcTemplate;
	@Setter
	private String sqlAdd;
	@Setter
	private Map<String,String> sqlMap;
	
	//새터이면서 JdbcCotnext에 대한 생성,DI작업을 동시에 수행한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}

	private RowMapper<User> userMapper=new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user=new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	};
	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject(this.sqlMap.get("get"), new Object[] {id},this.userMapper);
	}
	
	public List<User> getAll() {
		return this.jdbcTemplate.query(this.sqlMap.get("getAll"), this.userMapper);
	}
	
	public void add(final User user) throws DuplicateKeyException{
		this.jdbcTemplate.update(this.sqlMap.get("add"), 
			user.getId(),user.getName(),user.getPassword(),user.getLevel().intValue(),user.getLogin(),user.getRecommend(),user.getEmail());
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlMap.get("deleteAll"));
	}

	public int getCount() {
		return this.jdbcTemplate.queryForInt(this.sqlMap.get("getCount"));
	}

	public void update(User user) {
		this.jdbcTemplate.update(this.sqlMap.get("update"),
				user.getName(),user.getPassword(),user.getLevel().intValue(),user.getLogin(),user.getRecommend(),user.getEmail(),user.getId());
	}
	

}

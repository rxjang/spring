package com.bit.sts03.dept.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bit.sts03.dept.model.entity.DeptVo;

import lombok.Setter;


@Repository
public class DeptDaoImpl implements DeptDao {
	JdbcTemplate jdbcTemplate;
	private RowMapper<DeptVo> rowMapper=new RowMapper<DeptVo>() {

		@Override
		public DeptVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new DeptVo(
					rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc")
					);
		}
	};
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<DeptVo> selectAll(){
		String sql="select * from dept03 order by deptno";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	public DeptVo selectOne(int deptno) {
		String sql="select * from dept03 where deptno=?";
		return jdbcTemplate.queryForObject(sql, rowMapper,deptno);
	}
	
	public void insertOne(DeptVo bean) throws SQLException {
		String sql="insert into dept03 (dname,loc) values (?,?)";
		jdbcTemplate.update(sql,bean.getDname(),bean.getLoc());
	}

	@Override
	public int updateOne(DeptVo bean) throws SQLException {
		String sql="update dept03 set dname=?,loc=? where deptno=?";
		return jdbcTemplate.update(sql,bean.getDname(),bean.getLoc(),bean.getDeptno());
	}

	@Override
	public int zDeleteOne(int deptno) throws SQLException {
		String sql="Delete from dept03 where deptno=?";
		return jdbcTemplate.update(sql,deptno);
	}
	
}

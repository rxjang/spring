package com.bit.framework.dept.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.bit.framework.dept.model.entity.DeptVo;

public class DeptDaoImpl extends JdbcDaoSupport implements DeptDao {

	RowMapper rowMapper=new RowMapper<DeptVo>() {

		@Override
		public DeptVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new DeptVo(rs.getInt("deptno"),rs.getString("dname"),rs.getString("loc"));
		}
		
	};
	
	@Override
	public List<DeptVo> selectAll() throws SQLException {
		String sql="select * from dept";
		return getJdbcTemplate().query(sql,rowMapper);
	}

	@Override
	public void insertOne(DeptVo bean) throws SQLException {
		String sql="insert into dept (dname,loc) values (?,?)";
		getJdbcTemplate().update(sql,new Object[] {bean.getDname(),bean.getLoc()});
	}

	@Override
	public DeptVo selectOne(int key) throws SQLException {
		String sql="select * from dept where deptno=?";
		return getJdbcTemplate().queryForObject(sql, new Object[] {key},rowMapper);
	}

	@Override
	public int updateOne(DeptVo bean) throws SQLException {
		String sql="update dept set dname=?,loc=? where deptno=?";
		return getJdbcTemplate().update(sql,new Object[] {bean.getDname(),bean.getLoc(),bean.getDeptno()});
	}

	@Override
	public int deleteOne(int key) throws SQLException {
		String sql="delete from dept where deptno=?";
		return getJdbcTemplate().update(sql,new Object[] {key});
	}

}

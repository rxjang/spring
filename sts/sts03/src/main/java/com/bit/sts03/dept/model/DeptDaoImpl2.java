package com.bit.sts03.dept.model;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.bit.sts03.dept.model.entity.DeptVo;

public class DeptDaoImpl2 extends SqlMapClientDaoSupport implements DeptDao {
	

	@Override
	public List<DeptVo> selectAll() throws SQLException {
		return getSqlMapClient().queryForList("selectAll");
	}

	@Override
	public DeptVo selectOne(int deptno) throws SQLException {
		return (DeptVo) getSqlMapClient().queryForObject("selectOne",deptno);
	}

	@Override
	public void insertOne(DeptVo bean) throws SQLException {
		getSqlMapClient().insert("insertOne",bean);
	}

	@Override
	public int updateOne(DeptVo bean) throws SQLException {
		return getSqlMapClient().update("updateOne",bean);
	}

	@Override
	public int zDeleteOne(int deptno) throws SQLException {
		return getSqlMapClient().delete("deleteOne",deptno);
	}

}

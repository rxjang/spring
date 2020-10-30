package com.bit.sts03.dept.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.sts03.dept.model.entity.DeptVo;

public interface DeptDao {

	List<DeptVo> selectAll() throws SQLException;
	DeptVo selectOne(int deptno) throws SQLException;
	public void insertOne(DeptVo bean) throws SQLException;
	int updateOne(DeptVo bean) throws SQLException;
	int zDeleteOne(int deptno) throws SQLException;
}
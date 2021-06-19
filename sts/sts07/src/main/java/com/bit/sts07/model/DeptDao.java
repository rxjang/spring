package com.bit.sts07.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.sts07.model.entity.DeptVo;

public interface DeptDao {
	List<DeptVo> selectAll() throws SQLException;
	int selectTot() throws SQLException;
	DeptVo selectOne(int deptno) throws SQLException;
	void insertOne(DeptVo bean) throws SQLException;
	int updateOne(DeptVo bean) throws SQLException;
	int deleteOne(int deptno) throws SQLException;
}

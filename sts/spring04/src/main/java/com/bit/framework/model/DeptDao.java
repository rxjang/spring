package com.bit.framework.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.framework.model.entity.DeptVo;

public interface DeptDao {
	List<DeptVo> selectAll() throws SQLException;
	DeptVo selectOne(int key) throws SQLException;
	void insertOne(DeptVo bean) throws SQLException;
	int updateOne(DeptVo bean) throws SQLException;
	int deleteOne(int key) throws SQLException;
}

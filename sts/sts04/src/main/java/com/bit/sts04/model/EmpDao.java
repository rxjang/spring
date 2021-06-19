package com.bit.sts04.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.sts04.model.entity.EmpVo;

public interface EmpDao {
	
	List<EmpVo> selectAll() throws SQLException;
	EmpVo selectOne(int key) throws SQLException;
	void insertOne(EmpVo bean) throws SQLException;
	int updateOne(EmpVo bean) throws SQLException;
	int deleteOne(int key) throws SQLException;
	
}

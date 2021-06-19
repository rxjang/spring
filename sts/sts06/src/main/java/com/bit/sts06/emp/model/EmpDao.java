package com.bit.sts06.emp.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.sts06.emp.model.entity.EmpVo;

public interface EmpDao {
	List<EmpVo> selectAll() throws SQLException;
	List<EmpVo> selectAll(int page) throws SQLException;
	EmpVo selectOne(int sabun) throws SQLException;
	void insertOne(EmpVo bean) throws SQLException; 

}

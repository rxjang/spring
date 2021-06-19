package com.bit.framework.service;

import java.sql.SQLException;
import java.util.List;

import com.bit.framework.model.entity.DeptVo;

public interface DeptService {
	List<DeptVo> list() throws SQLException;
	void insert(DeptVo bean) throws SQLException;
	DeptVo detail(int deptno) throws SQLException;
}

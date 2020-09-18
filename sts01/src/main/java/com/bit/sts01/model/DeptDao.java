package com.bit.sts01.model;

import java.sql.SQLException;
import java.util.List;

import com.bit.sts01.model.entity.DeptVo;

public interface DeptDao {

	List<DeptVo> selectAll() throws SQLException;

}
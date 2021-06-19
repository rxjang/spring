package com.bit.sts07.service;

import java.sql.SQLException;

import org.springframework.ui.Model;

import com.bit.sts07.model.entity.DeptVo;

public interface DeptService {

	void listService(Model model) throws SQLException;

	void oneAddService(DeptVo bean) throws SQLException;

	void detailService(Model model, int deptno) throws SQLException;

	void oneEditService(DeptVo bean) throws SQLException;

	void oneDelService(int deptno) throws SQLException;

}

package com.bit.sts06.emp.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.bit.sts06.emp.model.entity.EmpVo;

public interface EmpService {
	void list(HttpServletRequest req) throws SQLException;
	void list(Model model) throws SQLException;
	void list(Model model,int page) throws SQLException;
	void insert(EmpVo bean) throws SQLException;
	
}

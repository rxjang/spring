package com.bit.framework.dept.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.framework.dept.model.DeptDao;

public class OneController implements Controller{
	DeptDao deptDao;
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int deptno=Integer.parseInt(request.getParameter("idx"));
		return new ModelAndView("dept/detail","bean",deptDao.selectOne(deptno));
	}
}

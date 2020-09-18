package com.bit.framework.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bit.framework.emp.model.EmpDao;

public class UpdateController implements Controller {
	EmpDao dao;
	
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int empno=Integer.parseInt(request.getParameter("empno"));
		String name=request.getParameter("name");
		String sub=request.getParameter("sub");
		int pay=Integer.parseInt(request.getParameter("pay"));
		dao.updateOne(empno,name,sub,pay);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:detail.bit?idx="+empno);
		return mav;
	}

}

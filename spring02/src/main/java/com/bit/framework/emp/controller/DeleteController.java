package com.bit.framework.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bit.framework.emp.model.EmpDao;

public class DeleteController implements Controller {
	EmpDao dao;
	
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		dao.deleteOne(Integer.parseInt(request.getParameter("idx")));
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:list.bit");
		return mav;
	}

}

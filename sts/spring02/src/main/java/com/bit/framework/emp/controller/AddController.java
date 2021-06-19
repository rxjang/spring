package com.bit.framework.emp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bit.framework.emp.model.EmpDao;

public class AddController implements Controller {
	EmpDao dao;
	
	public void setDao(EmpDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav=new ModelAndView();
		if("GET".contentEquals(request.getMethod())) {
			mav.setViewName("emp/add");
		}else {
			String name=request.getParameter("name");
			String sub=request.getParameter("sub");
			int pay=Integer.parseInt(request.getParameter("pay"));
			dao.insertOne(name,sub,pay);
			mav.setViewName("redirect:list.bit");
		}
		return mav;
	}

}

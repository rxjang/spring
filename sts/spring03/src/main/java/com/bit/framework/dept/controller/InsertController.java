package com.bit.framework.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bit.framework.dept.model.DeptDao;
import com.bit.framework.dept.model.entity.DeptVo;

public class InsertController extends AbstractCommandController{
	//extends AbstractController {
	Logger log=Logger.getLogger(this.getClass());
	DeptDao deptDao;
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {
		DeptVo bean=(DeptVo)command;
		if(bean.getDname()==null) {
			ObjectError error=new ObjectError("dname","이름을 반드시 입력하세요");
			errors.addError(error);
		}else if(bean.getDname().isEmpty()) {
			ObjectError error=new ObjectError("dname","이름을 반드시 입력하세요");
			errors.addError(error);
		}
		
		if(bean.getLoc()==null) {
			ObjectError error=new ObjectError("loc","지역을 반드시 입력하세요");
			errors.addError(error);
		}else if(bean.getLoc().isEmpty()) {
			ObjectError error=new ObjectError("loc","지역을 반드시 입력하세요");
			errors.addError(error);
		}
		log.debug(errors);
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
//		String dname=request.getParameter("dname");
//		String loc=request.getParameter("loc");
		
//		log.debug("dname: "+dname+",loc: "+loc );
		String path="redirect:/dept/list.bit";
//		log.debug(command);
		DeptVo bean=(DeptVo)(command);
//		if(errors.hasErrors()) {
//			path="dept/add";
//		}else {
//			deptDao.insertOne(bean);
//		}
		if(errors.getErrorCount()==0) {
			deptDao.insertOne(bean);
		}else {
			path="dept/add";
		}
		return new ModelAndView(path);
	}


}

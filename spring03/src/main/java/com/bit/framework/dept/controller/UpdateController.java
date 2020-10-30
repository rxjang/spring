package com.bit.framework.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bit.framework.dept.model.DeptDao;
import com.bit.framework.dept.model.entity.DeptVo;

public class UpdateController extends AbstractCommandController {
   
   DeptDao deptDao;
   
   public void setDeptDao(DeptDao deptDao) {
      this.deptDao = deptDao;
   }
   
   @Override
   protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
         BindException errors) throws Exception {

      deptDao.updateOne((DeptVo)command);
      return new ModelAndView("redirect:list.bit");
   }
}
package com.bit.sts07;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bit.sts07.model.entity.DeptVo;
import com.bit.sts07.service.DeptService;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	DeptService deptService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String list(Model model) {
		try {
			deptService.listService(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "dept/list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String insert(@ModelAttribute DeptVo bean) {
		try {
			deptService.oneAddService(bean);
		} catch (SQLException e) {
			return "dept/add";
		}
		return "redirect:./";
	}
	
	@RequestMapping(value="/add")
	public void add() {}
	
	@RequestMapping(value="/{deptno}",method=RequestMethod.GET)
	public String detail(@PathVariable int deptno,Model model) throws SQLException{
		deptService.detailService(model,deptno);
		return "dept/detail";
	}
	
	@RequestMapping("/{deptno}/edit")
	public String edit(@PathVariable int deptno,Model model) throws SQLException {
		deptService.detailService(model, deptno);
		return "dept/edit";
	}
	
	@RequestMapping(value="/{deptno}", method=RequestMethod.PUT)
	public ModelAndView update(@PathVariable int deptno,@ModelAttribute DeptVo bean) throws SQLException{
		deptService.oneEditService(bean);
		return new ModelAndView("redirect:./"+deptno);
	}
	
	@RequestMapping(value="/{deptno}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int deptno) throws SQLException{
		deptService.oneDelService(deptno);
		return "redirect:./";
	}
}

package com.bit.sts03.dept.controller;

import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.sts03.dept.model.DeptDao;
import com.bit.sts03.dept.model.entity.DeptVo;

@Controller
@RequestMapping("/dept")
public class DeptController {
	
	@Autowired
	DeptDao deptDao;
	
	@RequestMapping("/")
	public String list(Model model) throws SQLException {
		model.addAttribute("list",deptDao.selectAll());
		return "dept/list";
	}
	
	@RequestMapping("/{deptno}")
	public String one(@PathVariable("deptno") int deptno,Model model) throws SQLException {
		model.addAttribute("title", "Detail");
		model.addAttribute("bean",deptDao.selectOne(deptno));
		return "dept/form";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("title","add");
		return "dept/form";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String insert(@ModelAttribute("bean") DeptVo bean) throws SQLException {
		deptDao.insertOne(bean);
		return "redirect:./";
	}
	
}

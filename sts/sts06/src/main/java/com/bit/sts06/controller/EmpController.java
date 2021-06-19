package com.bit.sts06.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.sts06.emp.model.entity.EmpVo;
import com.bit.sts06.emp.service.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	EmpService empService;
	
	@RequestMapping(value="/")
	public String list(HttpServletRequest req,Model model) throws SQLException{
		empService.list(req);
		return "emp/list";
	}
	
	@RequestMapping(value="/",params="page")
	public String list(HttpServletRequest req,Model model,int page) throws SQLException{
		empService.list(req);
		return "emp/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("title","Add");
		return "emp/form";
	}
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public String add(@ModelAttribute EmpVo bean) throws SQLException {
		empService.insert(bean);
		return "redirect:./";
	}
}

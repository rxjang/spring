package com.bit.sts04.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.sts04.model.EmpDao;
import com.bit.sts04.model.entity.EmpVo;

@Controller
@RequestMapping("/emp")
public class Emp03Controller {
	
	@Autowired
	EmpDao empDao;
	
	@RequestMapping("/")
	public String list(Model model) throws SQLException {
		model.addAttribute("list",empDao.selectAll());
		return "emp/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public void add() {}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute EmpVo bean){
		try {
			empDao.insertOne(bean);
		} catch (SQLException e) {
			return "emp/add";
		}
		return "redirect:./";
	}
	
	@RequestMapping("/{sabun}")
	public String detail(@PathVariable int sabun, Model model) throws SQLException {
		model.addAttribute("bean",empDao.selectOne(sabun));
		model.addAttribute("title", "detail");
		return "emp/add";
	}
}

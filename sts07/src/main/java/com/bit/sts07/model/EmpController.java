package com.bit.sts07.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.sts07.service.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {
	@Autowired
	EmpService empService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String list(Model model) throws SQLException {
		empService.listServic(model);
		return "emp/list";
	}
}

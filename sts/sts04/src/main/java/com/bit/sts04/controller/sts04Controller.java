package com.bit.sts04.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sts04Controller {
	@Autowired
	DataSource dataSource;
	
	@RequestMapping("/")
	public String index(Model model) throws SQLException {
		System.out.println(dataSource.getConnection());
		String[] arr= {
				
			"library.jpeg",
			"lib-account.jpeg"
		};
		model.addAttribute("imgs",arr);
		return "home";
	}
}

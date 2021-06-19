package com.bit.sts01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ex06Controller {
	
	@RequestMapping("/ex06/*")
	public String ex01() {
		return "ex06";
	}
	
	@RequestMapping("/ex06")
	public String ex02() {
		return "ex06";
	}
}

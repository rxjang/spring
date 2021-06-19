package com.bit.sts01.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class Ex03Controller {
	
	@RequestMapping("/ex03")
	public  String ex03() {
		return "home";
	}

//	@RequestMapping
//	public String ex04() {
//		return "ex01";
//	}
}

package com.bit.sts01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.sts01.model.entity.TestBean;

@Controller
public class Ex09Controller {

	@RequestMapping("/ex09")
	public String ex01() {
		System.out.println("arg없음");
		return "ex09";
	}
	
	@RequestMapping("/ex10")
	public String ex01(HttpServletRequest req) {
		String id=req.getParameter("id");
		System.out.println("id:"+req.getParameter(("id")));
		req.setAttribute("id", id);
		return "ex09";
	}
	
	@RequestMapping("/ex11")
	public String ex01(HttpServletRequest req,Model model) {
		model.addAttribute("id",req.getParameter("id"));
		return "ex09";
	}
	
	@RequestMapping("/ex12")	//RequestParam생략가능
	public String ex01(int su) {
		System.out.println((su+1));
		return "ex09";
	}
	
	@RequestMapping("/ex131")	
	public String ex01(@RequestParam("su") int su,@RequestParam("id") String id) {
		System.out.println(id+":"+(su+1));
		return "ex09";
	}
	
	@RequestMapping("/ex13/{num}")	//@PathVariable생략불가
	public String ex02(@PathVariable("num") int su) {
		System.out.println(su);
		return "ex09";
	}
	
	@RequestMapping("/ex14")
	public String ex03(@ModelAttribute("id") String id) {
		id="ABCD";
		return "ex09";
	}
	
	@RequestMapping("/ex15")
	public String ex04(@ModelAttribute("bean") TestBean bean) {
		System.out.println(bean);
		return "ex15";
	}
}

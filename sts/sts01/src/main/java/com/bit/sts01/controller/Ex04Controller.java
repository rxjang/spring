package com.bit.sts01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Ex04Controller {
	
	//get방식으로 할 시 get이외에는 접근 불가(post도 마찬가지)
	@RequestMapping(value="/ex04",method=RequestMethod.GET,params={"id","pw"})
	public String ex04(HttpServletRequest req) {
		System.out.println(req.getParameter("id")+","+req.getParameter("pw"));
		return "ex04";
	}
	@RequestMapping(value="/ex04",method=RequestMethod.GET)
	public String ex04() {
		System.out.println("요청없음");
		return "ex01";
	}
	@RequestMapping("/ex/ex05")
	public String ex02() {
		return"ex05";
	}
}

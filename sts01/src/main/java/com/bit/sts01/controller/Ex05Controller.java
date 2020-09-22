package com.bit.sts01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/test") //return "text/ex05"
@RequestMapping("/")
public class Ex05Controller {

	@RequestMapping("ex05")//클래스 requestMapping에서 "/"를 줬기때문에
	public void ex01() {
	//void로 했을때 ex05.jsp 보여줌
	}
	
	@RequestMapping("ex/ex06")	//return "ex/ex07"
	public void ex02() {}
}

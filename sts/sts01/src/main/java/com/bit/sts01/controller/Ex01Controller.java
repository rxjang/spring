package com.bit.sts01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller
public class Ex01Controller{
/*
 * Plain Old Java Object, 본래 자바의 장점을 살리는 '오래된' 방식의 '순수한' 자바객체를 말함.
 * 객체지향적인 원리에 충실하면서, 환경과 기술에 종속되지 않고 필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트
 */
	
	@RequestMapping("/ex01.bit")
//	@Override
	public ModelAndView ex01() {
		
//		new Ex01Controller().handleRequest(request, response);
		return new ModelAndView("ex01");
		
		
	}

}

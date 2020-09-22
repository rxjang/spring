package com.bit.login;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping("/emp/page2")
	public String page2() {
//	public String page2(HttpSession session) {
//		if("admin".equals(session.getAttribute("auth")))
//			return "page2";
//		else if("member".equals(session.getAttribute("auth")))
//			return "page2";
//		else
			return "page2";
	}
	
	@RequestMapping("/dept/page3")
	public String page3() {
//		if("admin".equals(session.getAttribute("auth")))
//			return "page3";
//		else
//			return "redirect:/";
		return "page3";
	}
	
	@RequestMapping("/page4")
	public void page4(HttpSession session) {
		System.out.println("controller"+session.getId());
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpSession session) {
//		HttpSession session=req.getSession();
		if(session.getAttribute("login")==null)
			return "login";
		else 
			return "redirect:/";
	}

	@RequestMapping("/result")
	public String result(String id,String pw,HttpServletRequest req,HttpSession session) {
		logger.info("id: "+id+", pw: "+pw);
//		HttpSession session=req.getSession();
		session.setAttribute("login", true);
		session.setAttribute("who", id);
		if(id.equals("admin"))
		session.setAttribute("auth", "admin");
		else if(id.equals("user01"))
			session.setAttribute("auth", "member");
		else
			session.setAttribute("auth", "amonymous");
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpSession session) {
//		HttpSession session=req.getSession();
		session.invalidate();//세션 갱신
		return "redirect:/";
	}
	
}

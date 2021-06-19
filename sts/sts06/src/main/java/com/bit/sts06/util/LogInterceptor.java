package com.bit.sts06.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LogInterceptor extends HandlerInterceptorAdapter {
	
	Logger log=LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//handler : 내가 호출하는 컨트롤러의 정보
		log.debug("before controller..."+handler);
//		if(request.getSession().getAttribute("login")==null) {
//			request.getRequestDispatcher("/login/").forward(request, response);
//			return false;
//		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("after controller..."
			+modelAndView.getModel()+"\r\n"+modelAndView.getViewName());
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("after view mapping"+ex);
		if(ex!=null) {
			response.sendRedirect("add");
		}
	}
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("비동기 요청시...");
	}
	
	
}
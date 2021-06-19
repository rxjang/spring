package com.bit.sts07.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository
//@Aspect
public class DaoAop {
	Logger log=LoggerFactory.getLogger(this.getClass());
	
//	@Before("execution(* com.bit.sts07.service.*Impl.*(..))")
	public void before() {
		log.debug("before dao...");
	}
	
//	@AfterReturning("execution(* com.bit.sts07.service.*Impl.list*(..))")
	public void afterReturn() {
		log.debug("after success...");
	}
}

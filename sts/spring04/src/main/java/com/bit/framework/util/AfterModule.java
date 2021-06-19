package com.bit.framework.util;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterModule implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("module 동작 후 작동..."+returnValue);
		
	}

}

package com.bit.framework.util;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeModule02 implements MethodBeforeAdvice {
/*
module 동작 전 작동...
method: func01
[]
객체(target): com.bit.framework.service.Module02@45820e51
안녕하세요
 */
	public void before(Method method, Object[] args, Object target) throws Throwable {
			System.out.println("module 동작 전 작동...");
			
	}

}

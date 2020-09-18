package com.bit.framework.util;

import org.springframework.aop.ThrowsAdvice;

public class ThrowModule implements ThrowsAdvice {
	public void afterThrowing(Exception ex) {
		System.out.println("module 수행 시 exception 발생"+ex.toString());
	}
}

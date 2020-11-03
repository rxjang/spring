package com.bit.springbook.learningtest.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
	Object target;
	
	public UppercaseHandler(Object target) {
		this.target=target;
	}
	//어떤 종류의 인터페이스를 구현한 타깃에도 적용 가능하도록 Object타입으로 수정
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Object ret=method.invoke(target, args);//타깃으로 위임. 인터페이스의 메소드 호출에 모두 적용된다.
		if(ret instanceof String && method.getName().startsWith("say")) {
			return ((String)ret).toUpperCase();
		}else {
			return ret;
		}
		//호출한 메소드의 리턴 타입이 String인 경우에만 대문자 변경 기능을 적용하도록 수정
	}

}

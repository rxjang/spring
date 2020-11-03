package com.bit.springbook.learningtest.jdk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class HelloTest {

	@Test
	public void simpleProxy() {
		Hello hello=new HelloTarget();
		assertThat(hello.sayHello("Toby"),is("Hello Toby"));
		assertThat(hello.sayHi("Toby"),is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"),is("Thank you Toby"));
		
		Hello proxyHello=new HelloUppercase(new HelloTarget());
		assertThat(proxyHello.sayHello("Toby"),is("HELLO TOBY"));
		assertThat(proxyHello.sayHi("Toby"),is("HI TOBY"));
		assertThat(proxyHello.sayThankYou("Toby"),is("THANK YOU TOBY"));
		
		Hello proxiedHello=(Hello)Proxy.newProxyInstance(
				//생성된 다이내믹 프록시 오브젝트는 Hello인터페이스를 구현하고 있으므로 Hello 타입으로 캐스팅해도 안전하다.
				getClass().getClassLoader(),//동적으로 생성되는 다이내믹 프록시 클래스의 로딩에 사용할 클래스 로더
				new Class[] {Hello.class},//구현할 인터페이스
				new UppercaseHandler(new HelloTarget()));//부가기능과 위임코드를 담은 InvocationHandler
		assertThat(proxiedHello.sayHello("Toby"),is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"),is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"),is("THANK YOU TOBY"));
	}

}

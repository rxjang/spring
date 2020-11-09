package com.bit.springbook.learningtest.jdk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

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

	@Test
	public void proxyFactoryBean() {
		ProxyFactoryBean pfBean= new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());	//타깃설정
		pfBean.addAdvice(new UppercaseAdvice()); //부가기능을 담은 어드바이스를 추가한다.
		
		Hello proxiedHello=(Hello) pfBean.getObject(); //FactoryBean이므로 getObject()로 생성된 프록시를 가져온다.
		assertThat(proxiedHello.sayHello("Toby"),is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"),is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"),is("THANK YOU TOBY"));
	}
	
	static class UppercaseAdvice implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			String ret=(String)invocation.proceed();
			//리플랙션의 메소드와 달리 메소드 실행 시 타깃 오브젝트를 전달할 필요가 없다.
			//MethodInvocation은 메소드 정보와 함께 타깃 오브젝트를 알고 있기 때문이다.
			return ret.toUpperCase();	//부가기능 적용
		}
	}
	
	@Test
	public void pointcutAdvisor() {
		ProxyFactoryBean pfBean=new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		
		NameMatchMethodPointcut pointcut= new NameMatchMethodPointcut();
		//메소드 이름을 비교해서 대상을 선정하는 알고리짐을 제공하는 포인트컷 생성
		pointcut.setMappedName("sayH*"); //이름 비교조건 설정
		
		pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
		//포인트컷과 어드바이스를 어드바이저로 묶어서 한번에 추가
		
		Hello proxiedHello=(Hello) pfBean.getObject();
		
		assertThat(proxiedHello.sayHello("Toby"),is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"),is("HI TOBY"));
		assertThat(proxiedHello.sayThankYou("Toby"),is("THANK YOU TOBY"));
		//매소드 이름이 포인트컷의 선정조건에 맞지 않으므로, 부가기능이 적용되지 않는다. 
		
	}
	
}

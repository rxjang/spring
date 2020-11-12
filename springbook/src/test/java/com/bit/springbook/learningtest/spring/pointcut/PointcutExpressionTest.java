package com.bit.springbook.learningtest.spring.pointcut;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointcutExpressionTest {
	
	@Test
	public void methodSignaturePointcut() throws NoSuchMethodException, SecurityException {
		AspectJExpressionPointcut pointcut= new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public int "+
				"com.bit.springbook.learningtest.spring.pointcut.Target.minus(int,int) "+
				"throws java.lang.RuntimeException)"); //Target클래스 minus()메소드 시그니처
		//Target.minus()
		assertThat(pointcut.getClassFilter().matches(Target.class)&&
				pointcut.getMethodMatcher().matches(
						Target.class.getMethod("minus", int.class,int.class), null), is(true));

		//Target.plus()
		assertThat(pointcut.getClassFilter().matches(Target.class)&&pointcut.getMethodMatcher().matches(
				Target.class.getMethod("plus", int.class,int.class), null),is(false));
		
		//Bean.method()
		assertThat(pointcut.getClassFilter().matches(Bean.class)&&
				pointcut.getMethodMatcher().matches(Target.class.getMethod("method"), null),is(false));
	}
}

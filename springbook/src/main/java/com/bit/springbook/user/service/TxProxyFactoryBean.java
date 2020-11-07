package com.bit.springbook.user.service;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.Setter;

@Setter
public class TxProxyFactoryBean implements FactoryBean<Object>{
	Object target;
	PlatformTransactionManager transactionManager;
	String pattern;
	Class<?> serviceInterface;
	//다이내 프록시를 생성할 때 필요하다. UserService외의 잉ㄴ터페이스를 가진 타깃에도 적용할 수 있다.
	
	//FactoryBean 인터페이스 구현 메소드
	@Override
	public Object getObject() throws Exception {
		TransactionHandler txHandler=new TransactionHandler();
		txHandler.setTarget(target);
		txHandler.setTransactionManager(transactionManager);
		txHandler.setPattern(pattern);
		return Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[] {serviceInterface},
				txHandler);
	}
	//DI 받은 정보를 이용해서 TransactionHandler을 사용하는 다이내믹 프록시를 생성한다.
	
	@Override
	public Class<?> getObjectType() {
		return serviceInterface;
	}
	//팩토리 빈이 생성하는 오브젝트의 타입은 DI받은 인터페이스 타입에 따라 달라진다. 따라서 다양한 타압의 프록시 오브젝트 생성에 재사용 할 수 있다.

	@Override
	public boolean isSingleton() {
		return false;
	}

}

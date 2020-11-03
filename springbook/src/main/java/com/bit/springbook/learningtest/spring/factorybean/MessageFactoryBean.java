package com.bit.springbook.learningtest.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

import lombok.Setter;

public class MessageFactoryBean implements FactoryBean<Message>{
	@Setter
	String text;
	//오브젝트를생성할 때 필요한 정보를 팩토리 빈의 프로퍼티로 설정해서 대신 DI받을 수 있게 한다. 주입된 정보는 오브젝트 생성 중에 사용된다.
	
	@Override
	public Message getObject() throws Exception {
		return Message.newMessage(this.text);
	}
	//실제 빈으로 사용될 오브젝트를 직접 생성한다. 코드를 이용하기 때문에 복잡한 방식의 오브젝트 생성과 초기화 작업도 가능하다.

	@Override
	public Class<?> getObjectType() {
		return Message.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	/*
	 * getObject()메소드가 돌려주는 오브젝트가 싱글톤인지를 알려준다.
	 * 이 팩토리 빈은 매번 요청할 때마다 새로운 오브젝트를 만드므로 false로 설정한다.
	 * 이것은 팻토리 빈의 동장방식에 관한 설정이고 만들어진 빈 오브젝트는 싱글톤으로 스프링이 관리해줄 수 있다. 
	 */
}

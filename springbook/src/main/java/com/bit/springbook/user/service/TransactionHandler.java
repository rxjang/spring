package com.bit.springbook.user.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import lombok.Setter;

public class TransactionHandler implements InvocationHandler {
	@Setter
	private Object target;//부가기능을 제공할 타깃 오브젝트. 어떤 타입의 오브젝트에도 적용 가능하다.
	@Setter
	private PlatformTransactionManager transactionManager;//트랜잭션 기능을 제공하는데 필요한 트랜잭션 매니져
	@Setter
	private String pattern; //트랜잭션을 적용할 메소드 이름 패턴
	
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().startsWith(pattern)) {
			return invokeInTransaction(method,args);
		}
		return null;
	}
	
	private Object invokeInTransaction(Method method,Object[] args) throws Throwable{
		TransactionStatus status=this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object ret=method.invoke(target, args);
			this.transactionManager.commit(status);
			return ret;
			//트랜잭션을 시작하고 타깃 오브젝트의 매소드를 호출한다. 예외가 발생하지 않았다면 커밋한다.
		}catch(InvocationTargetException e) {
			this.transactionManager.rollback(status);
			throw e.getTargetException();
		}
	}

}

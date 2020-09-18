package com.bit.framework.core;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bit.framework.model.entity.DeptVo;
import com.bit.framework.service.DeptService;

public class MainRun {
	public static void main(String[] args) throws SQLException {
//		IoC(제어의 역전, Inversion of Control)
//		Module02 module=new Module02(new EngConsoleService());
//		module.setConsole(new EngConsoleService());
//		module.func01();
//		module.func02();
		
		ApplicationContext ac=null;
//		ac=new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
//		ac=new GenericXmlApplicationContext("classpath:/applicationContext.xml");
		ac=new ClassPathXmlApplicationContext("/applicationContext.xml");
//		Module02 module=(Module02) ac.getBean("module02");//************모듈 생성 방법
//		module.func01();
//		module.func02();
		
//		Module03 module=(Module03)ac.getBean("module03");
//		System.out.println(module);
		
//		Module04 module=(Module04) ac.getBean("module");
//		module.ArrayShow();
//		module.setShow();
//		module.mapShow();
		
//		AOP(관점지향 프로그래밍)
//		Module02 module=(Module02) ac.getBean("proxyBean");
//		module.func01(1234,4321);
//		module.func02();
		
		DeptService deptService=(DeptService) ac.getBean("deptService");
//		for(DeptVo bean:deptService.list()) {
//			System.out.println(bean);
//		}
		deptService.insert(new DeptVo(0,"insertTest","insertTest2"));
//		System.out.println(deptService.detail(1));
	}
}

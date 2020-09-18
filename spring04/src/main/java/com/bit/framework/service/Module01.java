package com.bit.framework.service;

public class Module01 {
	
	public void func01() {
		EngConsoleService console=new EngConsoleService();
		console.sayHello();
	}
	
	public void func02() {
		EngConsoleService console=new EngConsoleService();
		for (int i = 0; i < 5; i++) {
			console.sayHi();
		}
	}
}

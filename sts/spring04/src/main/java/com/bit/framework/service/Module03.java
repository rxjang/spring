package com.bit.framework.service;

public class Module03 {
	int su1;
	double su2;
	String name;
	boolean tf;
	char ch;
	
	public void setSu1(int su1) {
		this.su1 = su1;
	}
	public void setSu2(double su2) {
		this.su2 = su2;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTf(boolean tf) {
		this.tf = tf;
	}
	public void setCh(char ch) {
		this.ch = ch;
	}
	
	
	@Override
	public String toString() {
		return "Module3 [su1=" + su1 + ", su2=" + su2 + ", name=" + name + ", tf=" + tf + ", ch=" + ch + "]";
	}
	
}

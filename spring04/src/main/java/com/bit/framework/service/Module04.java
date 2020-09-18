package com.bit.framework.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Module04 {
	String[] arr1;
	List<String> list;
	Set<String> set;
	Map<String, String> map;
	
	public void setArr1(String[] arr1) {
		this.arr1 = arr1;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	public void ArrayShow() {
		for(String msg:arr1)
			System.out.println(msg);
	}
	
	public void listShow() {
		for (String msg:list) {
			System.out.println(msg);
		}
	}
	
	public void setShow() {
		Iterator<String> ite=set.iterator();
		while(ite.hasNext())System.out.println(ite.next());
	}
	
	public void mapShow() {
		Set<Entry<String,String>> entrys=map.entrySet();
		Iterator<Entry<String,String>> ite=entrys.iterator();
		while(ite.hasNext()) {
			Entry<String,String> entry =ite.next();
			System.out.println(entry.getKey()+entry.getValue());
		}
	}
}

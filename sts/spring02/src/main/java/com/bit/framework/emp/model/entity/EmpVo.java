package com.bit.framework.emp.model.entity;

import java.sql.Date;

public class EmpVo {
	int empno;
	String name,sub;
	Date nalja;
	int pay;
	
	public EmpVo() {
	}

	public EmpVo(int empno, String name, String sub, Date nalja, int pay) {
		super();
		this.empno = empno;
		this.name = name;
		this.sub = sub;
		this.nalja = nalja;
		this.pay = pay;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public Date getNalja() {
		return nalja;
	}

	public void setNalja(Date nalja) {
		this.nalja = nalja;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "EmpVo [empno=" + empno + ", name=" + name + ", sub=" + sub + ", nalja=" + nalja + ", pay=" + pay + "]";
	}

	
	
}

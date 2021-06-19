package com.bit.sts02.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Setter
public class DeptVo {
	private int deptno;
	private String dname,loc;
	
	public DeptVo() {
	}
	
}

package com.bit.sts06.emp.model.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmpVo {
	private int sabun,pay,deptno;
	private String name;
	@EqualsAndHashCode.Exclude
	private Timestamp nalja;
}

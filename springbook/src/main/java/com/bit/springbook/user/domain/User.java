package com.bit.springbook.user.domain;

import java.util.Date;

import com.bit.springbook.user.dao.Level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	String id;
	String name;
	String password;
	Level level;
	int login;
	int recommend;
	
	public void upgradeLevel() {
		Level nextLevel=this.level.nextLevel();
		if(nextLevel==null) {
			throw new IllegalStateException(this.level+"은 업그레이드가 불가능합니다");
		}else {
			this.level=nextLevel;
		}
	}
}

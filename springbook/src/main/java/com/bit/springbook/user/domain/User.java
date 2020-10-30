package com.bit.springbook.user.domain;

import java.util.Date;

import com.bit.springbook.user.dao.Level;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	@NonNull String id;
	@NonNull String name;
	@NonNull String password;
	@NonNull Level level;
	@NonNull int login;
	@NonNull int recommend;
	@NonNull String email;
	
	public void upgradeLevel() {
		Level nextLevel=this.level.nextLevel();
		if(nextLevel==null) {
			throw new IllegalStateException(this.level+"은 업그레이드가 불가능합니다");
		}else {
			this.level=nextLevel;
		}
	}
}

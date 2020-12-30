package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberVO {

	private int mno;
	private String mid;
	private String mpw;
	private String mname;
	private Timestamp regdate;
	
	
	public MemberVO(int mno, String mid, String mpw, String mname, Timestamp regdate) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.regdate = regdate;
	}
	
	
	
}

package task.model.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sus_01Vo {
	private int id; //sus_01점포의 상품 식별 번호
	private String pname; //상품 이름
	private int quantity; //상품 수량
	private String info; //상품 정보
	private Date createtime; //상품 생성 시각
	private Date updatetime; //상품 정보 업데이트 시각
	private String category; //상품의 카테고리 이름
	private int deleted; //상품의 삭제 여부
	
	public Sus_01Vo( String pname, int quantity, String info, Date createtime, String category) {
		super();
		this.pname = pname;
		this.quantity = quantity;
		this.info = info;
		this.createtime = createtime;
		this.category = category;
	}
	
	
}

package task.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	private int id;	//카테고리 식별 번호
	private String cname; //카테고리 이름
	private int deleted; //카테고리 삭제 여부
}

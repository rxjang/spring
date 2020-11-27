package task.service;

import java.util.List;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

public interface SusService {
	
	Sus_01Vo getOneSus_01(int id); //sus_01점포의 한 상품을 조회합니다.

	Sus_02Vo getOneSus_02(int id); //sus_01점포의 한 상품을 조회합니다.
	
	List<Sus_01Vo> getAllSus_01(); //sus_01의 모든 상품을 조회합니다.

	List<Sus_02Vo> getAllSus_02(); //Sus_02의 모든 상품을 조회합니다.
	
	void add(Sus_01Vo sus_01Vo); //새 상품 정보를 sus_01점포에 등록 시, sus_02점포에도 등록합니다.
	
	void addToSus_02(Sus_02Vo sus_02Vo); //sus_02점포에 새 상품을 등록합니다.
	
	void update(Sus_01Vo sus_01Vo); //sus_01점포의 상품 업데이트 시, sus_02점포의 상품도 같이 업데이트 됩니다.

	void update(Sus_02Vo sus_02Vo); //sus_02점포의 상품을 업데이트 합니다.
	
	void deleteProduct(String tableName,int id); //해당 점포의 원하는 상품을 삭제합니다.
	
}

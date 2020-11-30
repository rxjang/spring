package task.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

public interface Sus_01Dao {
	List<Sus_01Vo> selectSus_01() throws DataAccessException;
	//모든 상품 조희
	
	Sus_01Vo selectOneSus_01(int id) throws DataAccessException;
	//한 개의 상품 조희
	
	void insetToSus_01(Sus_01Vo bean) throws DataAccessException;
	//상품 입력
	
	void updateSus_01(Sus_01Vo bean) throws DataAccessException;
	//상품 정보 업데이트
	
	void deleteSus_01(int id) throws DataAccessException;
	//상품 삭제

}

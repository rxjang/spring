package task.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

public interface Sus_02Dao {
	List<Sus_02Vo> selectSus_02() throws DataAccessException;
	//모든 상품 조희
	
	Sus_02Vo selectOneSus_02(int id) throws DataAccessException;
	//한 개의 상품 조희
	
	Sus_02Vo selectOneSus_02ByFK(int sus_01_id) throws DataAccessException;
	//sus_01점포의 id를 사용하여 검색
	
	void insetToSus_02(Sus_02Vo bean) throws DataAccessException;
	//상품 입력
	
	void insetToSus_02(Sus_01Vo bean,int sus_01_id) throws DataAccessException;
	//sus_01테이블에 상품 입력시 상품 입력
	
	void updateSus_02(Sus_02Vo bean) throws DataAccessException;
	//상품 정보 업데이트

	void updateSus_02(Sus_01Vo bean) throws DataAccessException;
	//sus_01테이블의 상품 업데이트시 상품 업데이트
	
	void deleteSus_02(int id) throws DataAccessException;
	//상품 삭제
	
}

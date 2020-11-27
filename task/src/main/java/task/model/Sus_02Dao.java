package task.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

import task.model.entity.Sus_02Vo;

public interface Sus_02Dao {
	List<Sus_02Vo> selectSus_02() throws DataAccessException;
	
	void insetToSus_02(Sus_02Vo bean) throws DataAccessException;
	
	void updateSus_02(Sus_02Vo bean) throws DataAccessException;
	
	void deleteSus_02(int id) throws DataAccessException;

}

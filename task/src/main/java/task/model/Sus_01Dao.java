package task.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

public interface Sus_01Dao {
	List<Sus_01Vo> selectSus_01() throws DataAccessException;
	
	void insetToSus_01(Sus_01Vo bean) throws DataAccessException;
	
	void updateSus_01(Sus_01Vo bean) throws DataAccessException;
	
	void deleteSus_01(int id) throws DataAccessException;
	
}

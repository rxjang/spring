package task.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

public interface SusDao {
	List<Sus_01Vo> selectSus_01() throws DataAccessException;
	List<Sus_02Vo> selectSus_02() throws DataAccessException;
	
	void insetToSus_01(Sus_01Vo bean) throws DataAccessException;
	void insetToSus_02(Sus_02Vo bean) throws DataAccessException;
	
	void updateSus_01(Sus_01Vo bean) throws DataAccessException;
	void updateSus_02(Sus_02Vo bean) throws DataAccessException;
	
	void deleteSus_01(int id) throws DataAccessException;
	void deleteSus_02(int id) throws DataAccessException;
	
}

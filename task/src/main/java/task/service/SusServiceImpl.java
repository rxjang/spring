package task.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.model.Sus_01Dao;
import task.model.entity.Sus_01Vo;

@Service
public class SusServiceImpl implements SusService {

	@Autowired SqlSession sqlSession;
	
	public List<Sus_01Vo> getAllSus_01() {
		Sus_01Dao susDao=sqlSession.getMapper(Sus_01Dao.class);
		List<Sus_01Vo> sus_01List=susDao.selectSus_01();
		return sus_01List;
	}

}

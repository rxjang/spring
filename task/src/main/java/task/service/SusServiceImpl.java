package task.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import task.model.Sus_01Dao;
import task.model.Sus_02Dao;
import task.model.entity.Sus_01Vo;
import task.model.entity.Sus_02Vo;

@Service
public class SusServiceImpl implements SusService {

	@Autowired SqlSession sqlSession;
	
	Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
	Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);

	public Sus_01Vo getOneSus_01(int id) {
		return sus_01Dao.selectOneSus_01(id);
	}
	
	public Sus_02Vo getOneSus_02(int id) {
		return sus_02Dao.selectOneSus_02(id);
	}
	
	public List<Sus_01Vo> getAllSus_01() {
		return sus_01Dao.selectSus_01();
	}

	public List<Sus_02Vo> getAllSus_02() {
		return sus_02Dao.selectSus_02();
	}

	public void add(Sus_01Vo sus_01Vo) {
		sus_01Dao.insetToSus_01(sus_01Vo);
		int sus_01_id=sus_01Vo.getId();
		sus_02Dao.insetToSus_02(sus_01Vo,sus_01_id);
		//sus_01점포에 새 상품을 등록시,sus_02점포에도 등록되게 한다
	}

	public void addToSus_02(Sus_02Vo sus_02Vo) {
		sus_02Dao.insetToSus_02(sus_02Vo);
	}

	public void update(Sus_01Vo sus_01Vo) {
		sus_01Dao.updateSus_01(sus_01Vo);
		Sus_02Vo sus_02Vo= sus_02Dao.selectOneSus_02ByFK(sus_01Vo.getId());
		sus_01Vo.setId(sus_02Vo.getId());
		sus_02Dao.updateSus_02(sus_01Vo);
	}

	public void update(Sus_02Vo sus_02Vo) {
		sus_02Dao.updateSus_02(sus_02Vo);
	}

	public void deleteProduct(String tableName, int id) {
		//테이블 이름의 끝 두 글자를 확인해 테이블을 구분하여 쿼리문 실행
		if(tableName.endsWith("01")) {
			sus_01Dao.deleteSus_01(id);
		}else {
			sus_02Dao.deleteSus_02(id);
		}
		
	}

}

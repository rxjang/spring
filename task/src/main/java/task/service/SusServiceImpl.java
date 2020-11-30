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
	

	public Sus_01Vo getOneSus_01(int id) {
		Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
		return sus_01Dao.selectOneSus_01(id);
	}
	
	public Sus_02Vo getOneSus_02(int id) {
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		return sus_02Dao.selectOneSus_02(id);
	}
	
	public List<Sus_01Vo> getAllSus_01() {
		Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
		return sus_01Dao.selectSus_01();
	}

	public List<Sus_02Vo> getAllSus_02() {
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		return sus_02Dao.selectSus_02();
	}

	/************************요구사항 1번 실행 함수*****************************/
	public void add(Sus_01Vo sus_01Vo) {
		Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		sus_01Dao.insetToSus_01(sus_01Vo);
		//sus_01점포에 상품 입력
		Sus_02Vo sus_02Vo=new Sus_02Vo();
		int sus_02Id;
		if(sus_02Dao.selectSus_02().size()==0) {
			sus_02Id=1;
		}else {
			sus_02Id=sus_02Dao.selectLastId()+1;
		}
		//id 삽입을 위해 마지막 id값 계산
		sus_02Vo.setId(sus_02Id);
		sus_02Vo.setPname(sus_01Vo.getPname());
		sus_02Vo.setQuantity(sus_01Vo.getQuantity());
		sus_02Vo.setCreatetime(sus_01Vo.getCreatetime());
		sus_02Vo.setInfo(sus_01Vo.getInfo());
		sus_02Vo.setCategory(sus_01Vo.getCategory());
		sus_02Vo.setSus_01_id(sus_01Vo.getId());
		sus_02Dao.insetToSus_02(sus_02Vo);
		//sus_01점포에 새 상품을 등록시,sus_02점포에도 등록되게 한다
	}

	public void addToSus_02(Sus_02Vo sus_02Vo) {
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		sus_02Dao.insetToSus_02(sus_02Vo);
	}

	/************************요구사항 2번 실행 함수*****************************/
	public void update(Sus_01Vo sus_01Vo) {
		Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		sus_01Dao.updateSus_01(sus_01Vo);
		Sus_02Vo sus_02Vo= sus_02Dao.selectOneSus_02ByFK(sus_01Vo.getId());
		//업데이트 된 sus_01점포의 상품의 아이디를 사용하여 sus_02점포의 같은 상품 아이디 조회
		sus_01Vo.setId(sus_02Vo.getId());
		//업데이트 될 정보 중, id를 sus_02점포의 id로 바꿔 해당 상품 정보 업데이트
		sus_02Dao.updateSus_02(sus_01Vo);
	}

	public void update(Sus_02Vo sus_02Vo) {
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		sus_02Dao.updateSus_02(sus_02Vo);
	}

	public void deleteProduct(String tableName, int id) {
		//테이블 이름의 끝 두 글자를 확인해 테이블을 구분하여 쿼리문 실행
		Sus_01Dao sus_01Dao=sqlSession.getMapper(Sus_01Dao.class);
		Sus_02Dao sus_02Dao=sqlSession.getMapper(Sus_02Dao.class);
		if(tableName.endsWith("01")) {
			sus_01Dao.deleteSus_01(id);
		}else {
			sus_02Dao.deleteSus_02(id);
		}
		
	}

}

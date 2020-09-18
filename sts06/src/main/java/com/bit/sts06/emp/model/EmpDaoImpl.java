package com.bit.sts06.emp.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.sts06.emp.model.entity.EmpVo;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<EmpVo> selectAll() throws SQLException {
		return sqlSession.selectList("emp.selectAll");
	}

	@Override
	public List<EmpVo> selectAll(int page) throws SQLException {
		return sqlSession.selectList("emp.selectAll",page);
	}

	@Override
	public EmpVo selectOne(int sabun) throws SQLException {
		return null;
	}

	@Override
	public void insertOne(EmpVo bean) throws SQLException {
		sqlSession.insert("emp.insertOne",bean);
	}

}

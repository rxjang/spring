package com.bit.sts07.service;


import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bit.sts07.model.DeptDao;
import com.bit.sts07.model.entity.DeptVo;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	SqlSession sqlSession;

	@Override
	public void listService(Model model) throws SQLException {
		DeptDao dao=sqlSession.getMapper(DeptDao.class);
		model.addAttribute("list",dao.selectAll());
	}

	@Override
	public void oneAddService(DeptVo bean) throws SQLException {
		DeptDao dao=sqlSession.getMapper(DeptDao.class);
		dao.insertOne(bean);
		
	}

	@Override
	public void detailService(Model model, int deptno) throws SQLException {
		DeptDao dao=sqlSession.getMapper(DeptDao.class);
		model.addAttribute("bean",dao.selectOne(deptno));
	}

	@Override
	public void oneEditService(DeptVo bean) throws SQLException {
		DeptDao dao=sqlSession.getMapper(DeptDao.class);
		dao.updateOne(bean);
	}

	@Override
	public void oneDelService(int deptno) throws SQLException {
		DeptDao dao=sqlSession.getMapper(DeptDao.class);
		dao.deleteOne(deptno);
	}

}

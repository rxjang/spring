package com.bit.sts07.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bit.sts07.model.EmpDao;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void listServic(Model model) throws SQLException {
		EmpDao dao=sqlSession.getMapper(EmpDao.class);
		model.addAttribute("list",dao.selectAll());
	}

}

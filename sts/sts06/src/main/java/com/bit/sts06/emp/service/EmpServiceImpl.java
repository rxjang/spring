package com.bit.sts06.emp.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bit.sts06.emp.model.EmpDao;
import com.bit.sts06.emp.model.entity.EmpVo;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	EmpDao empDao;
	
	@Override
	public void list(HttpServletRequest req) throws SQLException {
		System.out.println("list(req)");
		if(req.getParameter("page")==null)
			req.setAttribute("list", empDao.selectAll());
		else
			req.setAttribute("list", empDao.selectAll(Integer.parseInt(req.getParameter("page"))));
		req.setAttribute("begin", 1);
	}

	@Override
	public void list(Model model) throws SQLException {
		System.out.println("list(model)");
		model.addAttribute("list",empDao.selectAll());
		model.addAttribute("begin",1);
	}

	@Override
	public void list(Model model, int page) throws SQLException {
		System.out.println("list(model,page)");
		model.addAttribute("list",empDao.selectAll(page));
		model.addAttribute("begin",1);
		
	}

	@Override
	public void insert(EmpVo bean) throws SQLException {
		System.out.println("list(bean)");
		empDao.insertOne(bean);
		
	}

}

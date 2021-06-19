package com.bit.sts07.service;

import java.sql.SQLException;

import org.springframework.ui.Model;


public interface EmpService {
	void listServic(Model model) throws SQLException;
}

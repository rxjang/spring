package com.bit.sts08.model.service;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;

public interface DeptService {
	void list(Model model) throws DataAccessException;
}

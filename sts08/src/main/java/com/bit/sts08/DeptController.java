package com.bit.sts08;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.sts08.model.service.DeptService;
import com.google.gson.Gson;

@Controller
public class DeptController {
	@Inject
	DeptService deptService;

	@RequestMapping(value = "/dept/",method=RequestMethod.GET)
	public @ResponseBody String listPage(Model model) {
		try {
			deptService.list(model);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		return gson.toString();
	}
}
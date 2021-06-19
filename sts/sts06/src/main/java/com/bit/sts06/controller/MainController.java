package com.bit.sts06.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	Logger log=LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String index() {
		log.debug("mainController");
		return "index";
	}
}
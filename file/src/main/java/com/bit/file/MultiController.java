package com.bit.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MultiController {
	Logger log=LoggerFactory.getLogger(MultiController.class);

	@RequestMapping("/multi")
	public void form() {}
	
	@RequestMapping(value="/uploads", method=RequestMethod.POST)
	public String upload(@RequestParam("files") MultipartFile[] files,Model model) throws IllegalStateException, IOException {
		List<String> names=new ArrayList<String>();
		for(MultipartFile file:files) {
			String origin=System.currentTimeMillis()+"_"+file.getOriginalFilename();
			if(origin.isEmpty())continue;
			File dest=new File("/Users/seohyunj/libs/upload"+origin);
			file.transferTo(dest);
			names.add(origin);
		}
		model.addAttribute("names",names);
		return "downloads";
	}
}

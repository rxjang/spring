package com.bit.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String path="/Users/seohyunj/libs/upload/";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(int sabun,MultipartFile file1,Model model) {
		logger.info("sabun="+sabun+"");
		logger.info("file1="+file1.getOriginalFilename());
		String orgName=file1.getOriginalFilename();
		String reName=System.currentTimeMillis()+"_"+orgName;
		
		File dest=new File(path+reName);
		
		try {
			file1.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("name",reName);
		return "download";
	}
	
	@RequestMapping("/download/{name:.+}")//홧장자 포함 pathvariable
	@ResponseBody
	public void download(@PathVariable String name,HttpServletResponse res) {
		logger.info(name);
		String origin=name.substring(name.indexOf('_')+1);
		File file=new File(path+name);
		if(file.exists()) {
			OutputStream os=null;
			InputStream is=null;
			res.setContentType("application/octet-stream");
			res.setHeader("content-Disposition", "attachment; filename=\""+origin+"\"");
			try {
				os=res.getOutputStream();
				is=new FileInputStream(file);
				int su=-1;
				while(((su=is.read())!=-1))os.write(su);
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(is!=null)is.close();
					if(os!=null)os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}

package com.zxh.webOnlinePreview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
	private final static Logger LOGGER = LoggerFactory.getLogger(indexController.class);
	
	@GetMapping(value = "index")
	public String index() {
		return "index";
		
	}
	
	@GetMapping(value = "/")
	public String redi() {
		return "redirect:/index";
	}
	
	
}

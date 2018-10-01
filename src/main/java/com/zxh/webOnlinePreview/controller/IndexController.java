package com.zxh.webOnlinePreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class IndexController {
	
	@GetMapping(value = "index")
	public String goIndex () {
		return "index";
	}
	
	@GetMapping(value = "/")
	public String goRoot () {
		return "redirect:/index";
	}
}

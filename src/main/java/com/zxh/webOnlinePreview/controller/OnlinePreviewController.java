package com.zxh.webOnlinePreview.controller;

import javax.servlet.http.HttpServletRequest;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zxh.webOnlinePreview.service.FilePreview;
import com.zxh.webOnlinePreview.service.FilePreviewFactory;

@Controller
public class OnlinePreviewController {
	
	@Autowired
	FilePreviewFactory  previewFactory;
	
	@Autowired
	RedissonClient redissonClient;
	
	@GetMapping(value = "onlinePreview")
	public String OnlinePreview(String url, Model model, HttpServletRequest request) {
		request.setAttribute("fileKey", request.getParameter("fileKey"));
		FilePreview filePreview = previewFactory.get(url);
		return filePreview.filePreviewHandle(url, model);
	}
	
	
}

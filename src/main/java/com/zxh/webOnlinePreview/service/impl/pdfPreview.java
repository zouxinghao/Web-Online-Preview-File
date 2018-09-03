package com.zxh.webOnlinePreview.service.impl;

import org.springframework.ui.Model;

import com.zxh.webOnlinePreview.service.FilePreview;

public class pdfPreview implements FilePreview{

	@Override
	public String filePreview(String url, Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("pdfUrl", url);
		return "pdf";
	}

}

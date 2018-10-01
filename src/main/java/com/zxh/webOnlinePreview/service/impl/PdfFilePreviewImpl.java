package com.zxh.webOnlinePreview.service.impl;

import org.springframework.ui.Model;

import com.zxh.webOnlinePreview.service.FilePreview;

public class PdfFilePreviewImpl implements FilePreview{
	
	@Override
	public String filePreviewHandle(String url, Model model) {
		model.addAttribute("pdfUrl", url);
		return "pdf";
	}
}

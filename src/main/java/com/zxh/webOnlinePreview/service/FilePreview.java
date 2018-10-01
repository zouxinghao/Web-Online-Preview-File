package com.zxh.webOnlinePreview.service;

import org.springframework.ui.Model;

public interface FilePreview {
	String FilePreviewHandle(String url, Model model);
}

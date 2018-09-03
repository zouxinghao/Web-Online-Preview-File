package com.zxh.webOnlinePreview.service;

import org.springframework.ui.Model;

public interface FilePreview {
	String filePreview(String url, Model model);
}

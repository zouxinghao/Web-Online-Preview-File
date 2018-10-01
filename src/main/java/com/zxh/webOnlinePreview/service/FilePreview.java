package com.zxh.webOnlinePreview.service;

import org.springframework.ui.Model;

public interface FilePreview {

	String filePreviewHandle(String url, Model model);
}

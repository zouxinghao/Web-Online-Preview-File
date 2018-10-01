package com.zxh.webOnlinePreview.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.zxh.webOnlinePreview.model.FileAttribute;
import com.zxh.webOnlinePreview.utils.FileUtils;

@Service
public class FilePreviewFactory {
	
	@Autowired
	FileUtils fileUtils;
	
	@Autowired
	ApplicationContext applicationContext;
	
	public FilePreview get(String url) {
		Map<String, FilePreview> filePreviewMap = applicationContext.getBeansOfType(FilePreview.class);
		FileAttribute fileAttribute = fileUtils.getFileAttribute(url);
        return filePreviewMap.get(fileAttribute.getType().getInstanceName());
	}
}

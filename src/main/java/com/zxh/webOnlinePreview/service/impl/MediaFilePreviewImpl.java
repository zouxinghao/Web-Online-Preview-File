package com.zxh.webOnlinePreview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.zxh.webOnlinePreview.service.FilePreview;
import com.zxh.webOnlinePreview.utils.FileUtils;

@Service
public class MediaFilePreviewImpl implements FilePreview{
	@Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model) {
        model.addAttribute("mediaUrl", url);
        return "media";
    }
}

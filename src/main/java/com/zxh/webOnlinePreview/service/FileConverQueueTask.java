package com.zxh.webOnlinePreview.service;


import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxh.webOnlinePreview.utils.FileUtils;

@Service
public class FileConverQueueTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileConverQueueTask.class);
	
	public static final String queueTaskName="FileConverQueueTask";
	
	@Autowired
	FilePreviewFactory previewFactory;
	
	@Autowired
    RedissonClient redissonClient;
	
	@Autowired
    FileUtils fileUtils;
}

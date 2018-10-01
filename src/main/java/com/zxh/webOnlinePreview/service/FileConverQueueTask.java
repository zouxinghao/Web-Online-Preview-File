package com.zxh.webOnlinePreview.service;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;

import com.zxh.webOnlinePreview.model.FileAttribute;
import com.zxh.webOnlinePreview.model.FileType;
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
	
	@PostConstruct
	public void startTask() {
		ExecutorService excutorService = Executors.newFixedThreadPool(3);
		excutorService.submit(new ConverTask(previewFactory,redissonClient,fileUtils));
		LOGGER.info("Processing queue initial completed");
	}
	
	class ConverTask implements Runnable {
		FilePreviewFactory previewFactory;

        RedissonClient redissonClient;

        FileUtils fileUtils;
        
        public ConverTask(FilePreviewFactory previewFactory, RedissonClient redissonClient,FileUtils fileUtils) {
            this.previewFactory = previewFactory;
            this.redissonClient = redissonClient;
            this.fileUtils=fileUtils;
        }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					final RBlockingQueue<String> queue = redissonClient.getBlockingDeque(FileConverQueueTask.queueTaskName);
					String url = queue.take();
					if(url!=null) {
						FileAttribute fileAttribute = fileUtils.getFileAttribute(url);
						LOGGER.info("正在处理转换任务，文件名称【{}】",fileAttribute.getName());
                        FileType fileType=fileAttribute.getType();
                        if(fileType.equals(FileType.compress) || fileType.equals(FileType.office)){
                            FilePreview filePreview=previewFactory.get(url);
                            filePreview.filePreviewHandle(url,new ExtendedModelMap());
					}
				}
			} catch (Exception e){
				try {
					Thread.sleep(1000*10);;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				e.printStackTrace();
			}
		}

		}       
	}
}

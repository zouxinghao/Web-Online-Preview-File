package com.zxh.webOnlinePreview.utils;

import java.util.List;
import java.util.Map;

import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.zxh.webOnlinePreview.dto.FileType;

import jodd.io.findfile.FindFile.FileNameComparator;

public class fileUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(fileUtils.class);
	
	private final String REDIS_FILE_PREVIEW_PDF_KEY = "converted-preview-pdf-file";
	private final String REDIS_FILE_PREVIEW_IMGS_KEY = "converted-preview-imgs-file";
	
	@Autowired
	RedissonClient redissonClient;
	
	@Value("$(file.dir)")
	String fileDir;
	
	/**
	 * Converted files (from redis)
	 * @return
	 */
	public Map<String, String> listConvertedFiles(){
		RMapCache<String, String> listFile = redissonClient.getMapCache(REDIS_FILE_PREVIEW_PDF_KEY);
		return listFile;
	}
	
	/**
	 * Get the value(file) by name
	 * @param key
	 * @return
	 */
	public String getConvertedFile(String key) {
		RMapCache<String, String> listFile = redissonClient.getMapCache(REDIS_FILE_PREVIEW_PDF_KEY);
		return listFile.get(key);
	}
	
	public FileType getTypeFromUrl(String url) {
		String paraStr = url.substring(0, url.indexOf("?") == -1 ? url.length() : url.lastIndexOf("?"));
		String fileName = paraStr.substring(paraStr.lastIndexOf("/")+1);
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		if(listPictureTypes().contains(fileType.toLowerCase())) {
			return FileType.picture;
		}
		
	}
	
	public List<String> listPictureTypes(){
        List<String> list = Lists.newArrayList();
        list.add("jpg");
        list.add("jpeg");
        list.add("png");
        list.add("gif");
        list.add("bmp");
        list.add("ico");
        list.add("RAW");
        return list;
    }
	
	public List<String> listArchiveTypes(){
        List<String> list = Lists.newArrayList();
        list.add("rar");
        list.add("zip");
        list.add("jar");
        list.add("7-zip");
        list.add("tar");
        list.add("gzip");
        list.add("7z");
        return list;
    }

    public List<String> listOfficeTypes() {
        List<String> list = Lists.newArrayList();
        list.add("docx");
        list.add("doc");
        list.add("xls");
        list.add("xlsx");
        list.add("ppt");
        list.add("pptx");
        return list;
    }
}

package com.zxh.webOnlinePreview.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zxh.webOnlinePreview.model.ReturnResponse;
import com.zxh.webOnlinePreview.utils.FileUtils;

/**
 * Using RestController for no-jumpping page, only display xml/jason info
 * @author xingh
 *
 */
@RestController
public class FileController {
	@Value("${file.dir}")
    String fileDir;
    @Autowired
    FileUtils fileUtils;
    
    String demoDir = "demo";
    String demoPath = demoDir + File.separator;
	
    @PostMapping(value = "fileUpload")
    public String UploadFile(@RequestParam("file") MultipartFile file, 
    		HttpServletRequest request) throws JsonProcessingException {
    	String fileName = file.getOriginalFilename();
    	
    	if(existsTypeFile(fileName)) {
    		return new ObjectMapper().writeValueAsString((new ReturnResponse<String>(1, "每一种类型只可以上传一个文件，请先删除原有文件再次上传", null)));
    	}
    	
    	File outFile = new File(fileDir + demoPath);
    	
    	if (!outFile.exists()) {
            outFile.mkdirs();
        }
    	try(InputStream in = file.getInputStream();
                OutputStream ot = new FileOutputStream(fileDir + demoPath + fileName)){
    			byte[] buffer = new byte[1024];
    			int len;
    			while((-1 != (len = in.read(buffer)))) {
    				ot.write(buffer, 0, len);
    			}
    			return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "SUCCESS", null));
    	} catch (IOException e) {
    		e.printStackTrace();
    		return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(1, "FAILURE", null));
    	}
    			
    }

    @GetMapping(value = "deleteFile")
    public String deleteFile(String fileName) throws JsonProcessingException {
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        File file = new File(fileDir + demoPath + fileName);
        if (file.exists()) {
            file.delete();
        }
        return new ObjectMapper().writeValueAsString(new ReturnResponse<String>(0, "SUCCESS", null));
    }

    @GetMapping(value = "listFiles")
    public String getFiles() throws JsonProcessingException {
        List<Map<String, String>> list = Lists.newArrayList();
        File file = new File(fileDir + demoPath);
        if (file.exists()) {
            Arrays.stream(file.listFiles()).forEach(file1 -> list.add(ImmutableMap.of("fileName", demoDir + "/" + file1.getName())));
        }
        return new ObjectMapper().writeValueAsString(list);
    }

    private String getFileName(String name) {
        String suffix = name.substring(name.lastIndexOf("."));
        String nameNoSuffix = name.substring(0, name.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        return uuid + "-" + nameNoSuffix + suffix;
    }
	private boolean existsTypeFile(String fileName) {
		// TODO Auto-generated method stub
		boolean result = false;
		String suffix = fileUtils.getSuffixFromFileName(fileName);
		File file = new File(fileDir + demoPath);
		if(file.exists()) {
			for(File test : file.listFiles()) {
				String existsFileSuffix = fileUtils.getSuffixFromFileName(test.getName());
				if(suffix.equals(existsFileSuffix)) {
					result = true;
				}
			}
		}
		return result;
	}
}

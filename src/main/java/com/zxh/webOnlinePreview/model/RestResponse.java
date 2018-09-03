package com.zxh.webOnlinePreview.model;

import java.io.Serializable;

public class RestResponse<T> implements Serializable{
	
	private static final long serialVersionUID = 313975329998789878L;
	
	/**
	 * return code;
	 */
	private boolean code;
	
	private String msg;
	
	private T content;
	
	public RestResponse(boolean code, String msg, T content) {
	       this.code = code;
	       this.msg = msg;
	       this.content = content;
	}
	
	public RestResponse(boolean code) {
		this.code = code;
	}
    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    
    public static RestResponse ok() {
        return new RestResponse(true);
    }
    
    public static RestResponse fail() {
        return new RestResponse(false);
    }
}

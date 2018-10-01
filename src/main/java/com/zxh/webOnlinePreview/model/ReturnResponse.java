package com.zxh.webOnlinePreview.model;

import java.io.Serializable;

public class ReturnResponse<T> implements Serializable {
	private static final long serialVersionUID = 313975329998789878L;
	
	/**
	 * return code
	 * 0: success
	 * 1: fail
	 */
	private int code;
	
	private String msg;
	
	private T content;
	
	public ReturnResponse(int code, String msg, T content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
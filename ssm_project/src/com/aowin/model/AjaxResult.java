package com.aowin.model;

public class AjaxResult {
	public static final int SUCCESS = 200;
	public static final int FAILURE = 0;
	
	//提示信息
	private String msg;
	//返回值
	private Object data;
	private int returnCode;
	public AjaxResult(boolean success, String msg, Object data){
		
		this.setResult(success);
		this.msg=msg;
		this.data=data;
	}
	
	public void setResult(boolean success) {
		if(success){
			this.returnCode =200;
		}else{
			this.returnCode = 0;
		}
	}

	public AjaxResult(boolean success, String msg) {
        this.setResult(success);
        this.msg = msg;
    }

    public AjaxResult(boolean success, Object data) {
        this.setResult(success);
        this.data = data;
    }
	
	public int getReturnCode() {
		return returnCode;
	}


	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AjaxResult [msg=" + msg + ", data=" + data + ", returnCode=" + returnCode + "]";
	}


	
}

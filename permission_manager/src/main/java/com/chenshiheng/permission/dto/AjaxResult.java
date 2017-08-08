package com.chenshiheng.permission.dto;

public class AjaxResult<T> {
	private String result;
	private T data;
	private String errorMsg;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public AjaxResult(String result, T data, String errorMsg) {
		this.result = result;
		this.data = data;
		this.errorMsg = errorMsg;
	}
	public AjaxResult(String result, String errorMsg) {
		this.result = result;
		this.errorMsg = errorMsg;
	}
	public AjaxResult() {
	}
	public AjaxResult(String result) {
		this.result = result;
	}
	
}

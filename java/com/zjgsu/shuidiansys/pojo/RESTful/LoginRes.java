package com.zjgsu.shuidiansys.pojo.RESTful;

public class LoginRes {
	
	private String code;
	
	private String msg;
	
	private Content content;
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Content getContent() {
		return content;
	}


	public void setContent(Content content) {
		this.content = content;
	}


	public class Content{
		
		private int type;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}
		
	}
}

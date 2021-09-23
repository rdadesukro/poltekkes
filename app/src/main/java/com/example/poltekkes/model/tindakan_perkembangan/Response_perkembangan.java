package com.example.poltekkes.model.tindakan_perkembangan;

import com.google.gson.annotations.SerializedName;

public class Response_perkembangan {

	@SerializedName("data")
	private Data_perkembangan data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(Data_perkembangan data){
		this.data = data;
	}

	public Data_perkembangan getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
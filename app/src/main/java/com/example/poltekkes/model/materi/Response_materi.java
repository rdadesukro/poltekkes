package com.example.poltekkes.model.materi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_materi {

	@SerializedName("data")
	private List<DataItem_materi> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataItem_materi> data){
		this.data = data;
	}

	public List<DataItem_materi> getData(){
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
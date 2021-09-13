package com.example.poltekkes.model.pertanyaan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_pertanyaan {

	@SerializedName("data")
	private List<DataItem_pertanyaan> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataItem_pertanyaan> data){
		this.data = data;
	}

	public List<DataItem_pertanyaan> getData(){
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
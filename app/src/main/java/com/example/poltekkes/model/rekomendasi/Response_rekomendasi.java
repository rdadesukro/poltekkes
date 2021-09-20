package com.example.poltekkes.model.rekomendasi;

import com.google.gson.annotations.SerializedName;

public class Response_rekomendasi {

	@SerializedName("data")
	private Data_rekomendasi data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(Data_rekomendasi data){
		this.data = data;
	}

	public Data_rekomendasi getData(){
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
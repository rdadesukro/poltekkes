package com.example.poltekkes.model.action;

import com.google.gson.annotations.SerializedName;

public class Response_action {

	@SerializedName("kode")
	private String kode;

	@SerializedName("message")
	private String message;

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
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
			"kode = '" + kode + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}
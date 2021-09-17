package com.example.poltekkes.model.umur;

import com.google.gson.annotations.SerializedName;

public class Response_balita {

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	@SerializedName("balita")
	private Balita balita;

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

	public void setBalita(Balita balita){
		this.balita = balita;
	}

	public Balita getBalita(){
		return balita;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			",balita = '" + balita + '\'' + 
			"}";
		}
}
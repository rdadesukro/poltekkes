package com.example.poltekkes.menu.model.login;

import com.google.gson.annotations.SerializedName;

public class Data_login {

	@SerializedName("user")
	private User user;

	@SerializedName("token")
	private String token;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"user = '" + user + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
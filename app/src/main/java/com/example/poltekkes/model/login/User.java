package com.example.poltekkes.model.login;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("nim")
	private String nim;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("username")
	private String username;

	public void setNim(String nim){
		this.nim = nim;
	}

	public String getNim(){
		return nim;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"nim = '" + nim + '\'' + 
			",nama_lengkap = '" + namaLengkap + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
package com.example.poltekkes.model.umur;

import com.google.gson.annotations.SerializedName;

public class Data_umur {

	@SerializedName("balita")
	private Balita_umur balita;

	public void setBalita(Balita_umur balita){
		this.balita = balita;
	}

	public Balita_umur getBalita(){
		return balita;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"balita = '" + balita + '\'' + 
			"}";
		}
}
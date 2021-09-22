package com.example.poltekkes.model.pertanyaan;

import com.google.gson.annotations.SerializedName;

public class DataItem_pertanyaan {

	@SerializedName("id")
	private int id;

	@SerializedName("text")
	private String text;

	@SerializedName("bulan")
	private int bulan;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setBulan(int bulan){
		this.bulan = bulan;
	}

	public int getBulan(){
		return bulan;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			",bulan = '" + bulan + '\'' + 
			"}";
		}
}
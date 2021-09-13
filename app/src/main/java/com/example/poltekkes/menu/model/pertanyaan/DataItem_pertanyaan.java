package com.example.poltekkes.menu.model.pertanyaan;

import com.google.gson.annotations.SerializedName;

public class DataItem_pertanyaan {

	@SerializedName("text")
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}
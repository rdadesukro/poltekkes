package com.example.poltekkes.model.materi;

import com.google.gson.annotations.SerializedName;

public class DataItem_materi {

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}
package com.example.poltekkes.model.detail_history;

import com.google.gson.annotations.SerializedName;

public class JawabanItem{

	@SerializedName("text")
	private String text;

	@SerializedName("jawaban")
	private String jawaban;

	@SerializedName("gambar")
	private String gambar;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setJawaban(String jawaban){
		this.jawaban = jawaban;
	}

	public String getJawaban(){
		return jawaban;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	@Override
 	public String toString(){
		return 
			"JawabanItem{" + 
			"text = '" + text + '\'' + 
			",jawaban = '" + jawaban + '\'' + 
			",gambar = '" + gambar + '\'' + 
			"}";
		}
}
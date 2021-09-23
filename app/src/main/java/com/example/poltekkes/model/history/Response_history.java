package com.example.poltekkes.model.history;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_history {

	@SerializedName("data")
	private List<DataItem_history> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("histori_jawaban ")
	private String historiJawaban;

	public void setData(List<DataItem_history> data){
		this.data = data;
	}

	public List<DataItem_history> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setHistoriJawaban(String historiJawaban){
		this.historiJawaban = historiJawaban;
	}

	public String getHistoriJawaban(){
		return historiJawaban;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",histori_jawaban  = '" + historiJawaban + '\'' + 
			"}";
		}
}
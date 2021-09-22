package com.example.poltekkes.model.pertanyaan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_pertanyaan {

	@SerializedName("usia_bulan")
	private int usiaBulan;

	@SerializedName("data")
	private List<DataItem_pertanyaan> data;

	@SerializedName("success")
	private boolean success;

	public void setUsiaBulan(int usiaBulan){
		this.usiaBulan = usiaBulan;
	}

	public int getUsiaBulan(){
		return usiaBulan;
	}

	public void setData(List<DataItem_pertanyaan> data){
		this.data = data;
	}

	public List<DataItem_pertanyaan> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"usia_bulan = '" + usiaBulan + '\'' + 
			",data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			"}";
		}
}
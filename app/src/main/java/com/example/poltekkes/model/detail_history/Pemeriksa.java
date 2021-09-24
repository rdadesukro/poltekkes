package com.example.poltekkes.model.detail_history;

import com.google.gson.annotations.SerializedName;

public class Pemeriksa{

	@SerializedName("nim")
	private String nim;

	@SerializedName("tanggal_pemeriksaan")
	private String tanggalPemeriksaan;

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

	public void setTanggalPemeriksaan(String tanggalPemeriksaan){
		this.tanggalPemeriksaan = tanggalPemeriksaan;
	}

	public String getTanggalPemeriksaan(){
		return tanggalPemeriksaan;
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
			"Pemeriksa{" + 
			"nim = '" + nim + '\'' + 
			",tanggal_pemeriksaan = '" + tanggalPemeriksaan + '\'' + 
			",nama_lengkap = '" + namaLengkap + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
package com.example.poltekkes.model.history;

import com.google.gson.annotations.SerializedName;

public class DataItem_history {

	@SerializedName("nama_balita")
	private String namaBalita;

	@SerializedName("tanggal_pemeriksaan")
	private String tanggalPemeriksaan;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	public void setNamaBalita(String namaBalita){
		this.namaBalita = namaBalita;
	}

	public String getNamaBalita(){
		return namaBalita;
	}

	public void setTanggalPemeriksaan(String tanggalPemeriksaan){
		this.tanggalPemeriksaan = tanggalPemeriksaan;
	}

	public String getTanggalPemeriksaan(){
		return tanggalPemeriksaan;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"nama_balita = '" + namaBalita + '\'' + 
			",tanggal_pemeriksaan = '" + tanggalPemeriksaan + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal_lahir = '" + tanggalLahir + '\'' + 
			"}";
		}
}
package com.example.poltekkes.model.detail_history;

import com.google.gson.annotations.SerializedName;

public class Balita{

	@SerializedName("panjang")
	private String panjang;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nama_ibu")
	private String namaIbu;

	public String getUsia() {
		return usia;
	}

	public void setUsia(String usia) {
		this.usia = usia;
	}

	@SerializedName("usia")
	private String usia;



	@SerializedName("berat")
	private String berat;

	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	@SerializedName("tanggal_lahir")
	private String tanggalLahir;

	@SerializedName("alamat")
	private String alamat;

	public void setPanjang(String panjang){
		this.panjang = panjang;
	}

	public String getPanjang(){
		return panjang;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNamaIbu(String namaIbu){
		this.namaIbu = namaIbu;
	}

	public String getNamaIbu(){
		return namaIbu;
	}

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"Balita{" + 
			"panjang = '" + panjang + '\'' + 
			",nama = '" + nama + '\'' + 
			",nama_ibu = '" + namaIbu + '\'' + 
			",berat = '" + berat + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			",tanggal_lahir = '" + tanggalLahir + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}
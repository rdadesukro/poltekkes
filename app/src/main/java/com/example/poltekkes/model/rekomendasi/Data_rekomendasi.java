package com.example.poltekkes.model.rekomendasi;

import com.google.gson.annotations.SerializedName;

public class Data_rekomendasi {

	@SerializedName("status_pertumbuhan")
	private String statusPertumbuhan;

	@SerializedName("berat_badan")
	private float beratBadan;

	@SerializedName("usia_dalam_bulan")
	private int usiaDalamBulan;

	@SerializedName("rekomendasi")
	private String rekomendasi;

	@SerializedName("rekomendasi_kode")
	private String rekomendasi_kode;

	public String getRekomendasi_kode() {
		return rekomendasi_kode;
	}

	public void setRekomendasi_kode(String rekomendasi_kode) {
		this.rekomendasi_kode = rekomendasi_kode;
	}

	public String getPertumbuhan_kode() {
		return pertumbuhan_kode;
	}

	public void setPertumbuhan_kode(String pertumbuhan_kode) {
		this.pertumbuhan_kode = pertumbuhan_kode;
	}

	@SerializedName("pertumbuhan_kode")
	private String pertumbuhan_kode;




	@SerializedName("jenis_kelamin")
	private String jenisKelamin;

	public void setStatusPertumbuhan(String statusPertumbuhan){
		this.statusPertumbuhan = statusPertumbuhan;
	}

	public String getStatusPertumbuhan(){
		return statusPertumbuhan;
	}

	public void setBeratBadan(int beratBadan){
		this.beratBadan = beratBadan;
	}

	public float getBeratBadan(){
		return beratBadan;
	}

	public void setUsiaDalamBulan(int usiaDalamBulan){
		this.usiaDalamBulan = usiaDalamBulan;
	}

	public int getUsiaDalamBulan(){
		return usiaDalamBulan;
	}

	public void setRekomendasi(String rekomendasi){
		this.rekomendasi = rekomendasi;
	}

	public String getRekomendasi(){
		return rekomendasi;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"status_pertumbuhan = '" + statusPertumbuhan + '\'' + 
			",berat_badan = '" + beratBadan + '\'' + 
			",usia_dalam_bulan = '" + usiaDalamBulan + '\'' + 
			",rekomendasi = '" + rekomendasi + '\'' + 
			",jenis_kelamin = '" + jenisKelamin + '\'' + 
			"}";
		}
}
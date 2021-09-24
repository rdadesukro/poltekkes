package com.example.poltekkes.model.detail_history;

import com.google.gson.annotations.SerializedName;

public class Pertumbuhan{

	@SerializedName("status_pertumbuhan")
	private String statusPertumbuhan;

	@SerializedName("kode_pertumbuhan")
	private String kodePertumbuhan;

	@SerializedName("status_rekomendasi")
	private String statusRekomendasi;

	@SerializedName("kode_rekomendasi")
	private String kodeRekomendasi;

	public void setStatusPertumbuhan(String statusPertumbuhan){
		this.statusPertumbuhan = statusPertumbuhan;
	}

	public String getStatusPertumbuhan(){
		return statusPertumbuhan;
	}

	public void setKodePertumbuhan(String kodePertumbuhan){
		this.kodePertumbuhan = kodePertumbuhan;
	}

	public String getKodePertumbuhan(){
		return kodePertumbuhan;
	}

	public void setStatusRekomendasi(String statusRekomendasi){
		this.statusRekomendasi = statusRekomendasi;
	}

	public String getStatusRekomendasi(){
		return statusRekomendasi;
	}

	public void setKodeRekomendasi(String kodeRekomendasi){
		this.kodeRekomendasi = kodeRekomendasi;
	}

	public String getKodeRekomendasi(){
		return kodeRekomendasi;
	}

	@Override
 	public String toString(){
		return 
			"Pertumbuhan{" + 
			"status_pertumbuhan = '" + statusPertumbuhan + '\'' + 
			",kode_pertumbuhan = '" + kodePertumbuhan + '\'' + 
			",status_rekomendasi = '" + statusRekomendasi + '\'' + 
			",kode_rekomendasi = '" + kodeRekomendasi + '\'' + 
			"}";
		}
}
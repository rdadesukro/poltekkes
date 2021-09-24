package com.example.poltekkes.model.detail_history;

import com.google.gson.annotations.SerializedName;

public class Perkembangan{

	@SerializedName("jadwal_pertumbuhan")
	private String jadwalPertumbuhan;

	@SerializedName("jadwal_perkembangan")
	private String jadwalPerkembangan;

	@SerializedName("tindakan")
	private String tindakan;

	@SerializedName("hasil")
	private String hasil;

	public void setJadwalPertumbuhan(String jadwalPertumbuhan){
		this.jadwalPertumbuhan = jadwalPertumbuhan;
	}

	public String getJadwalPertumbuhan(){
		return jadwalPertumbuhan;
	}

	public void setJadwalPerkembangan(String jadwalPerkembangan){
		this.jadwalPerkembangan = jadwalPerkembangan;
	}

	public String getJadwalPerkembangan(){
		return jadwalPerkembangan;
	}

	public void setTindakan(String tindakan){
		this.tindakan = tindakan;
	}

	public String getTindakan(){
		return tindakan;
	}

	public void setHasil(String hasil){
		this.hasil = hasil;
	}

	public String getHasil(){
		return hasil;
	}

	@Override
 	public String toString(){
		return 
			"Perkembangan{" + 
			"jadwal_pertumbuhan = '" + jadwalPertumbuhan + '\'' + 
			",jadwal_perkembangan = '" + jadwalPerkembangan + '\'' + 
			",tindakan = '" + tindakan + '\'' + 
			",hasil = '" + hasil + '\'' + 
			"}";
		}
}
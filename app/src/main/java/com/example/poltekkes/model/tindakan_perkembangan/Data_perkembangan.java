package com.example.poltekkes.model.tindakan_perkembangan;

import com.google.gson.annotations.SerializedName;

public class Data_perkembangan {

	@SerializedName("jadwal_pertumbuhan")
	private String jadwalPertumbuhan;

	@SerializedName("jadwal_perkembangan")
	private String jadwalPerkembangan;

	@SerializedName("hasil_perkembangan")
	private String hasilPerkembangan;

	@SerializedName("tindakan")
	private String tindakan;

	@SerializedName("kode_tindakan_perkembangan")
	private String kodeTindakanPerkembangan;

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

	public void setHasilPerkembangan(String hasilPerkembangan){
		this.hasilPerkembangan = hasilPerkembangan;
	}

	public String getHasilPerkembangan(){
		return hasilPerkembangan;
	}

	public void setTindakan(String tindakan){
		this.tindakan = tindakan;
	}

	public String getTindakan(){
		return tindakan;
	}

	public void setKodeTindakanPerkembangan(String kodeTindakanPerkembangan){
		this.kodeTindakanPerkembangan = kodeTindakanPerkembangan;
	}

	public String getKodeTindakanPerkembangan(){
		return kodeTindakanPerkembangan;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"jadwal_pertumbuhan = '" + jadwalPertumbuhan + '\'' + 
			",jadwal_perkembangan = '" + jadwalPerkembangan + '\'' + 
			",hasil_perkembangan = '" + hasilPerkembangan + '\'' + 
			",tindakan = '" + tindakan + '\'' + 
			",kode_tindakan_perkembangan = '" + kodeTindakanPerkembangan + '\'' + 
			"}";
		}
}
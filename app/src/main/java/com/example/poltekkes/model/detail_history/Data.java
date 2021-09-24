package com.example.poltekkes.model.detail_history;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pemeriksa")
	private Pemeriksa pemeriksa;

	@SerializedName("jawaban")
	private List<JawabanItem> jawaban;

	@SerializedName("balita")
	private Balita balita;

	@SerializedName("pertumbuhan")
	private Pertumbuhan pertumbuhan;

	@SerializedName("perkembangan")
	private Perkembangan perkembangan;

	public void setPemeriksa(Pemeriksa pemeriksa){
		this.pemeriksa = pemeriksa;
	}

	public Pemeriksa getPemeriksa(){
		return pemeriksa;
	}

	public void setJawaban(List<JawabanItem> jawaban){
		this.jawaban = jawaban;
	}

	public List<JawabanItem> getJawaban(){
		return jawaban;
	}

	public void setBalita(Balita balita){
		this.balita = balita;
	}

	public Balita getBalita(){
		return balita;
	}

	public void setPertumbuhan(Pertumbuhan pertumbuhan){
		this.pertumbuhan = pertumbuhan;
	}

	public Pertumbuhan getPertumbuhan(){
		return pertumbuhan;
	}

	public void setPerkembangan(Perkembangan perkembangan){
		this.perkembangan = perkembangan;
	}

	public Perkembangan getPerkembangan(){
		return perkembangan;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"pemeriksa = '" + pemeriksa + '\'' + 
			",jawaban = '" + jawaban + '\'' + 
			",balita = '" + balita + '\'' + 
			",pertumbuhan = '" + pertumbuhan + '\'' + 
			",perkembangan = '" + perkembangan + '\'' + 
			"}";
		}
}
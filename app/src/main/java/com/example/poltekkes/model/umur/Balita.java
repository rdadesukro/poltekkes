package com.example.poltekkes.model.umur;

import com.google.gson.annotations.SerializedName;

public class Balita{

	@SerializedName("usia_dalam_hari")
	private int usiaDalamHari;

	@SerializedName("usia_dalam_bulan")
	private int usiaDalamBulan;

	@SerializedName("usia_terbilang")
	private String usiaTerbilang;

	@SerializedName("rentang_usia")
	private String rentangUsia;

	public void setUsiaDalamHari(int usiaDalamHari){
		this.usiaDalamHari = usiaDalamHari;
	}

	public int getUsiaDalamHari(){
		return usiaDalamHari;
	}

	public void setUsiaDalamBulan(int usiaDalamBulan){
		this.usiaDalamBulan = usiaDalamBulan;
	}

	public int getUsiaDalamBulan(){
		return usiaDalamBulan;
	}

	public void setUsiaTerbilang(String usiaTerbilang){
		this.usiaTerbilang = usiaTerbilang;
	}

	public String getUsiaTerbilang(){
		return usiaTerbilang;
	}

	public void setRentangUsia(String rentangUsia){
		this.rentangUsia = rentangUsia;
	}

	public String getRentangUsia(){
		return rentangUsia;
	}

	@Override
 	public String toString(){
		return 
			"Balita{" + 
			"usia_dalam_hari = '" + usiaDalamHari + '\'' + 
			",usia_dalam_bulan = '" + usiaDalamBulan + '\'' + 
			",usia_terbilang = '" + usiaTerbilang + '\'' + 
			",rentang_usia = '" + rentangUsia + '\'' + 
			"}";
		}
}
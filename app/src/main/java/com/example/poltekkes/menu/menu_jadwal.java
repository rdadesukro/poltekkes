package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.presenter.pertanyaan;
import com.example.poltekkes.view.pertanyaan_view;
import com.github.squti.guru.Guru;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;

public class menu_jadwal extends AppCompatActivity implements pertanyaan_view {

    private Button btnPngggil2;
    private WebView txtHasil3;
    private WebView txtHasil;
    String jd_pertumbuhan,jd_perkembangan;
    com.example.poltekkes.presenter.pertanyaan pertanyaan;
    String nama_balita,nama_ibu,alamat,usia_dalam_bulan,jk,bb,pb,kode_pertumbuhan,kode_rekomendasi,kode_tindakan,jawban_Array;
    String tgl_lahir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_jadwal);
        initView();
        pertanyaan = new pertanyaan(this,menu_jadwal.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nama_balita = Guru.getString("nama", "false");
        nama_ibu = Guru.getString("nama_ibu", "false");
        tgl_lahir = Guru.getString("tgl_lahir", "false");
        alamat = Guru.getString("alamat", "false");
        usia_dalam_bulan = Guru.getString("bulan", "false");
        jk = Guru.getString("jenis_kelamin", "false");
        bb = Guru.getString("bb", "false");
        pb = Guru.getString("pb", "false");
        kode_pertumbuhan = Guru.getString("pertumbuhan_kode", "false");
        kode_rekomendasi = Guru.getString("Rekomendasi_kode", "false");
        kode_tindakan = Guru.getString("kode_tindakan_perkembangan", "false");
        jawban_Array = String.valueOf(menu_pertanyaan.jawaban);



        jd_pertumbuhan  = Guru.getString("jadwal_pertumbuhan", "false");
        jd_perkembangan  = Guru.getString("jadwal_perkembangan", "false");
        txtHasil3.requestFocus();
        txtHasil3.getSettings().setLightTouchEnabled(true);
        txtHasil3.getSettings().setJavaScriptEnabled(true);
        txtHasil3.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ jd_pertumbuhan, "text/html", "UTF-8", "");


        txtHasil.requestFocus();
        txtHasil.getSettings().setLightTouchEnabled(true);
        txtHasil.getSettings().setJavaScriptEnabled(true);
        txtHasil.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ jd_perkembangan, "text/html", "UTF-8", "");
        btnPngggil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pertanyaan.simpan_pertanyaan(nama_balita,
                        tgl_lahir,
                        nama_ibu,
                        alamat,
                        usia_dalam_bulan,
                        jk,
                        bb,
                        pb,
                        kode_pertumbuhan,
                        kode_rekomendasi,
                        kode_tindakan,
                        jawban_Array);

            }
        });
    }

    private void initView() {
        btnPngggil2 = findViewById(R.id.btn_pngggil2);
        txtHasil3 = findViewById(R.id.txt_hasil3);
        txtHasil = findViewById(R.id.txt_hasil);
    }

    @Override
    public void pertanyaan(List<DataItem_pertanyaan> pertanyaan) {

    }

    @Override
    public void status(String status, String pesan) {
        if (status.equals("1")){
            berhasil();
        }else {
            Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void rentang_usia(String rentang_usi) {

    }
    void berhasil(){

        SweetAlertDialog pDialog = new SweetAlertDialog(menu_jadwal.this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("Berhasil Simpan Data");
        pDialog.setCancelable(false);
        pDialog.setConfirmText("OK");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent goInput = new Intent(menu_jadwal.this, menu_utama.class);
                startActivity(goInput);
                CustomIntent.customType(menu_jadwal.this, "fadein-to-fadeout");
            }
        });

        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
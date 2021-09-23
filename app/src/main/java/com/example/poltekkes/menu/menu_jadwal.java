package com.example.poltekkes.menu;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;

public class menu_jadwal extends AppCompatActivity {

    private Button btnPngggil2;
    private WebView txtHasil3;
    private WebView txtHasil;
    String jd_pertumbuhan,jd_perkembangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_jadwal);
        initView();

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

    }

    private void initView() {
        btnPngggil2 = findViewById(R.id.btn_pngggil2);
        txtHasil3 = findViewById(R.id.txt_hasil3);
        txtHasil = findViewById(R.id.txt_hasil);
    }
}
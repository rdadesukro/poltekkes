package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;

import maes.tech.intentanim.CustomIntent;

public class menu_hasil_tindakaan extends AppCompatActivity {
    private Button btnPngggil2;
    private WebView txtHasil3;
    String hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_hasil_tindakaan);
        initView();
        hasil = Guru.getString("tindakan", "false");
        txtHasil3.requestFocus();
        txtHasil3.getSettings().setLightTouchEnabled(true);
        txtHasil3.getSettings().setJavaScriptEnabled(true);
        txtHasil3.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + hasil, "text/html", "UTF-8", "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnPngggil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(menu_hasil_tindakaan.this, menu_pertanyaan.class);
                startActivity(goInput);
                CustomIntent.customType(menu_hasil_tindakaan.this, "fadein-to-fadeout");


            }
        });
    }

    private void initView() {
        btnPngggil2 = findViewById(R.id.btn_pngggil2);
        txtHasil3 = findViewById(R.id.txt_hasil3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
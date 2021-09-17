package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;

import maes.tech.intentanim.CustomIntent;

public class menu_hasil_rekomendasi_pertumbuhan extends AppCompatActivity {

    private Button btnPngggil2;
    private TextView txtHasil3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_hasil_rekomendasi_pertumbuhan);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnPngggil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(menu_hasil_rekomendasi_pertumbuhan.this, menu_pertanyaan.class);
                startActivity(goInput);
                CustomIntent.customType(menu_hasil_rekomendasi_pertumbuhan.this, "fadein-to-fadeout");



            }
        });
    }

    private void initView() {
        btnPngggil2 = findViewById(R.id.btn_pngggil2);
        txtHasil3 = findViewById(R.id.txt_hasil3);
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
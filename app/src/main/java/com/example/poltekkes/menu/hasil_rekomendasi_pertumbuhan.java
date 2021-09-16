package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import maes.tech.intentanim.CustomIntent;

public class hasil_rekomendasi_pertumbuhan extends AppCompatActivity {

    private Button btnPngggil2;
    private TextView txtHasil3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_rekomendasi_pertumbuhan);
        initView();

        btnPngggil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(hasil_rekomendasi_pertumbuhan.this, menu_pertanyaan.class);
                startActivity(goInput);
                CustomIntent.customType(hasil_rekomendasi_pertumbuhan.this, "fadein-to-fadeout");



            }
        });
    }

    private void initView() {
        btnPngggil2 = findViewById(R.id.btn_pngggil2);
        txtHasil3 = findViewById(R.id.txt_hasil3);
    }
}
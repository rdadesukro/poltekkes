package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;

import maes.tech.intentanim.CustomIntent;

public class menu_youtube extends AppCompatActivity {

    private CardView cardDigital;
    private CardView cardDacin;
    private CardView cardTinggi;
    private CardView cardBaby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_youtube);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cardDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomIntent.customType(menu_youtube.this, "fadein-to-fadeout");
                Guru.putString("jenis", "9sQRLStpt0E");
                Guru.putString("link", "https://www.youtube.com/watch?v=9sQRLStpt0E");
                Intent intent = new Intent(menu_youtube.this, menu_vidio.class);
                startActivity(intent);
            }
        });
        cardDacin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomIntent.customType(menu_youtube.this, "fadein-to-fadeout");
                Guru.putString("jenis", "N9ot8taDSQA");
                Guru.putString("link", "https://www.youtube.com/watch?v=N9ot8taDSQA");
                Intent intent = new Intent(menu_youtube.this, menu_vidio.class);
                startActivity(intent);
            }
        });
        cardTinggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomIntent.customType(menu_youtube.this, "fadein-to-fadeout");
                Guru.putString("jenis", "L6m9M-otIdQ");
                Guru.putString("link", "https://www.youtube.com/watch?v=L6m9M-otIdQ");
                Intent intent = new Intent(menu_youtube.this, menu_vidio.class);
                startActivity(intent);
            }
        });
        cardBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomIntent.customType(menu_youtube.this, "fadein-to-fadeout");
                Guru.putString("jenis", "hT3qFlYqBg8");
                Guru.putString("link", "https://www.youtube.com/watch?v=hT3qFlYqBg8");
                Intent intent = new Intent(menu_youtube.this, menu_vidio.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        cardDigital = findViewById(R.id.card_digital);
        cardDacin = findViewById(R.id.card_dacin);
        cardTinggi = findViewById(R.id.card_tinggi);
        cardBaby = findViewById(R.id.card_baby);
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
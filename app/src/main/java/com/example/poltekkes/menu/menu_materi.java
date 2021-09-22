package com.example.poltekkes.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;

import maes.tech.intentanim.CustomIntent;

public class menu_materi extends AppCompatActivity {


    private CardView cardPertumbuhan;
    private CardView cardPerkembangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_materi);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cardPertumbuhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomIntent.customType(menu_materi.this, "fadein-to-fadeout");
                Intent intent = new Intent(menu_materi.this, menu_materi_pertumbuhan.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        cardPertumbuhan = (CardView) findViewById(R.id.card_pertumbuhan);
        cardPerkembangan = (CardView) findViewById(R.id.card_perkembangan);
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
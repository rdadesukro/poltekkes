package com.example.poltekkes.menu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.smarteist.autoimageslider.SliderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class menu_utama extends AppCompatActivity {

    @BindView(R.id.card_petujuk)
    CardView cardPetujuk;
    @BindView(R.id.card_history)
    CardView cardHistory;
    @BindView(R.id.card_materi)
    CardView cardMateri;
    @BindView(R.id.bener)
    SliderView bener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick({R.id.card_petujuk, R.id.card_history, R.id.card_materi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_petujuk:
                break;
            case R.id.card_history:
                break;
            case R.id.card_materi:
                break;
        }
    }
}
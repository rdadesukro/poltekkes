package com.example.poltekkes.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_slider;
import com.example.poltekkes.model.slider.DataItem_slider;
import com.example.poltekkes.presenter.aksi;
import com.example.poltekkes.presenter.slider;
import com.example.poltekkes.view.slider_view;
import com.github.squti.guru.Guru;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;

public class menu_utama extends AppCompatActivity implements slider_view {

    @BindView(R.id.card_petujuk)
    CardView cardPetujuk;
    @BindView(R.id.card_history)
    CardView cardHistory;
    @BindView(R.id.card_materi)
    CardView cardMateri;
    @BindView(R.id.bener)
    SliderView bener;

    ProgressDialog progressDialog;
    String nama, nim;
    private TextView txtNama;
    private TextView txtNis;
    com.example.poltekkes.presenter.slider slider;

    private com.example.poltekkes.adapter.adapter_slider adapter_slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);
        initView();
        getSupportActionBar().hide();
        nama = Guru.getString("nama", "false");
        nim = Guru.getString("nim", "false");
        slider = new slider(menu_utama.this,menu_utama.this);
        slider.get_slider();
        txtNama.setText(nama);
        txtNis.setText("NIM : "+nim);
    }

    @OnClick({R.id.card_petujuk, R.id.card_history, R.id.card_materi, R.id.card_keluar, R.id.card_spk, R.id.card_laporan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_petujuk:
                break;
            case R.id.card_spk:

                CustomIntent.customType(this, "fadein-to-fadeout");
                Intent intent = new Intent((Activity) this, menu_input_data.class);
                startActivity(intent);
                break;
            case R.id.card_history:
                CustomIntent.customType(this, "fadein-to-fadeout");
                intent = new Intent((Activity) this, menu_history.class);
                startActivity(intent);
                break;
            case R.id.card_materi:
                CustomIntent.customType(this, "fadein-to-fadeout");
                intent = new Intent((Activity) this, menu_materi.class);
                startActivity(intent);
                break;
            case R.id.card_laporan:
                CustomIntent.customType(this, "fadein-to-fadeout");
                intent = new Intent((Activity) this, menu_tentang.class);
                startActivity(intent);
                break;
            case R.id.card_keluar:
                SweetAlertDialog pDialog = new SweetAlertDialog(menu_utama.this, SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Apakah anda yakin ingin keluar ?");
                pDialog.setCancelable(false);
                pDialog.setConfirmText("Ya");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        aksi countryPresenter = new aksi(null, menu_utama.this);
                        countryPresenter.logout(progressDialog);
                        Guru.putString("role", "1");
                        Log.i("isi_token", "onViewClicked: " + Guru.getString("token_login", "false"));
//                        countryPresenter.hapus_token(Guru.getString("token_login", "false"));


                    }
                });
                pDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();

                break;
        }
    }

    private void initView() {
        txtNama = findViewById(R.id.txt_nama);
        txtNis = findViewById(R.id.txt_nis);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    public void slider(List<DataItem_slider> slider) {
        Log.i("data_slider", "slider: " + slider);
        adapter_slider = new adapter_slider(menu_utama.this, slider, "mas_1");
        bener.setSliderAdapter(adapter_slider);
        bener.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
        bener.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        bener.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        bener.setIndicatorSelectedColor(Color.WHITE);
        bener.setIndicatorUnselectedColor(Color.RED);
        bener.setScrollTimeInSec(4);
        bener.setAutoCycle(true);
        bener.startAutoCycle();
        //mRecycler.setAdapter(adapter);
        adapter_slider.notifyDataSetChanged();
    }
}
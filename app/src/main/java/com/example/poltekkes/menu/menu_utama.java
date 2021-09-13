package com.example.poltekkes.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.example.poltekkes.presenter.aksi;
import com.github.squti.guru.Guru;
import com.smarteist.autoimageslider.SliderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;

public class menu_utama extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        nama = Guru.getString("nama", "false");
        nim = Guru.getString("nim", "false");
        initView();
        txtNama.setText(nama);
        txtNis.setText("NIM : "+nim);
    }

    @OnClick({R.id.card_petujuk, R.id.card_history, R.id.card_materi, R.id.card_keluar, R.id.card_spk})
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
                break;
            case R.id.card_materi:
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
}
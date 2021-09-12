package com.example.poltekkes.menu.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.example.poltekkes.menu.presenter.aksi;
import com.github.squti.guru.Guru;
import com.smarteist.autoimageslider.SliderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick({R.id.card_petujuk, R.id.card_history, R.id.card_materi, R.id.card_keluar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_petujuk:
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
                        aksi countryPresenter = new aksi(null,menu_utama.this);
                        countryPresenter.logout(progressDialog);
                        Guru.putString("role", "1");
                        Log.i("isi_token", "onViewClicked: "+Guru.getString("token_login", "false"));
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
}
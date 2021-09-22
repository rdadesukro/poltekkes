package com.example.poltekkes.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_slider;
import com.example.poltekkes.model.slider.DataItem_slider;
import com.example.poltekkes.presenter.aksi;
import com.example.poltekkes.presenter.slider;
import com.example.poltekkes.view.slider_view;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jeevandeshmukh.glidetoastlib.GlideToast;
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
    private Toolbar toolbar2;
    BottomSheetDialog bottom_dialog;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        ButterKnife.bind(this);
        initView();

        setSupportActionBar(toolbar2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Page");
        }
        nama = Guru.getString("nama", "false");
        username =Guru.getString("username", "false");
        nim = Guru.getString("nim", "false");
        slider = new slider(menu_utama.this, menu_utama.this);
        slider.get_slider();
        txtNama.setText(nama);
        txtNis.setText("NIM : " + nim);
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
        toolbar2 = findViewById(R.id.toolbar2);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        MenuItem share = menu.findItem(R.id.setting);
        share.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bottom_dialog = new BottomSheetDialog(menu_utama.this);
                bottom_dialog.setTitle("Login");
                bottom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                bottom_dialog.setContentView(R.layout.dialog_edit_password);
                bottom_dialog.setCancelable(false);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                bottom_dialog.getWindow().setAttributes(lp);
                bottom_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                bottom_dialog.getWindow().setDimAmount(0.5f);
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


                Button pgl = (Button) bottom_dialog.findViewById(R.id.btn_pngggil);
                ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close);
                 EditText  pass_lama = (EditText) bottom_dialog.findViewById(R.id.edit_pw_lama);
                EditText pass_baru = (EditText) bottom_dialog.findViewById(R.id.edit_pw_baru);
                final EditText pass_baru2 = (EditText) bottom_dialog.findViewById(R.id.edit_konfirmasi);
                pass_lama.requestFocus();
                pgl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pass_lama.getText().toString().equals("")) {
                            //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);

                            new GlideToast.makeToast(menu_utama.this, "Password lama tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                            pass_lama.requestFocus();
                        } else if (pass_baru.getText().toString().trim().equals("")) {
                            new GlideToast.makeToast(menu_utama.this, "Password baru tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                            // Toast.makeText(menu_profil_pejabat_pejabat.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT);
                            pass_baru.requestFocus();
                        } else if (pass_baru2.getText().toString().trim().equals("")) {
                            new GlideToast.makeToast(menu_utama.this, "Password konfirmasi tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                            //Toast.makeText(menu_profil_pejabat_pejabat.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT);
                            pass_baru2.requestFocus();
                        } else if (!pass_baru.getText().toString().equals(pass_baru2.getText().toString())) {
                            new GlideToast.makeToast(menu_utama.this, "pastikan password baru dan konfirmasi password sama !", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();


                            // Toast.makeText(menu_profil_pejabat_pejabat.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT);
                            pass_baru2.requestFocus();
                        } else if (pass_baru.getText().toString().trim().length() < 6) {
                            //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT);
                            new GlideToast.makeToast(menu_utama.this, "Minimal Password Baru 6 Karketr", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();


                            pass_baru.requestFocus();
                        } else {

                            aksi countryPresenter = new aksi(null, menu_utama.this);
                            countryPresenter.update_password(pass_lama.getText().toString().trim(), pass_baru.getText().toString().trim(), progressDialog);



                        }


                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottom_dialog.dismiss();

                    }
                });

                bottom_dialog.show();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
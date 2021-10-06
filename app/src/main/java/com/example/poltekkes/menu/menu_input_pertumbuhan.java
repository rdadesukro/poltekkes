package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.example.poltekkes.presenter.rekomendasi;
import com.example.poltekkes.view.rekomendasi_view;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maes.tech.intentanim.CustomIntent;

public class menu_input_pertumbuhan extends AppCompatActivity implements Validator.ValidationListener, rekomendasi_view {

    private Button btnSimpan;
    private EditText editBerat;
    private EditText editPanjang;
    BottomSheetDialog bottom_dialog;
    Validator validator;

    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_berat)
    EditText edit_berat;

    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_panjang)
    EditText edit_panjang;
    com.example.poltekkes.presenter.rekomendasi rekomendasi;
    String hasil_rekomendasi,umur,jk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_pertumbuhan);
        initView();
        ButterKnife.bind(this);
        jk =   Guru.getString("jenis_kelamin", "false");
        umur =   Guru.getString("bulan", "false");
        rekomendasi = new rekomendasi(this,menu_input_pertumbuhan.this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();

            }
        });
    }

    private void initView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        editBerat = findViewById(R.id.edit_berat);
        editPanjang = findViewById(R.id.edit_panjang);
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

    @Override
    public void onValidationSucceeded() {
//        String berat = String.valueOf((Integer.valueOf(String.valueOf(editBerat.getText()))));
//        String value= editBerat.getText().toString();
//        Integer finalValue=Integer.parseInt(value);
//        String pajang = edit_panjang.getText().toString().trim();
        String number = editBerat.getText().toString().trim();
        rekomendasi.get_rekomendasi(number,jk,umur);


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void umur(String Status_pertumbuhan, String rekomendasi, String status,String pertumbuhan_kode,String Rekomendasi_kode) {
        if (status.equals("1")){
            bottom_dialog = new BottomSheetDialog(menu_input_pertumbuhan.this);
            bottom_dialog.setTitle("Login");
            bottom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bottom_dialog.setContentView(R.layout.dialog_hasil_pertumbuhan);
            bottom_dialog.setCancelable(false);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialog.getWindow().getAttributes());
            bottom_dialog.getWindow().setAttributes(lp);
            bottom_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            bottom_dialog.getWindow().setDimAmount(0.5f);
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


            Button pgl = (Button) bottom_dialog.findViewById(R.id.btn_pngggil);
            WebView hasil = (WebView) bottom_dialog.findViewById(R.id.txt_hasil);
            hasil.requestFocus();
            hasil.getSettings().setLightTouchEnabled(true);
            hasil.getSettings().setJavaScriptEnabled(true);
            hasil.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            hasil.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            hasil.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ Status_pertumbuhan, "text/html", "UTF-8", "");

            ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close);
            pgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottom_dialog.dismiss();
                    Guru.putString("rekomendasi",rekomendasi);
                    Guru.putString("pertumbuhan_kode",pertumbuhan_kode);
                    Guru.putString("Rekomendasi_kode",Rekomendasi_kode);
                    Guru.putString("bb", edit_berat.getText().toString().trim());
                    Guru.putString("pb", edit_panjang.getText().toString().trim());
                    Guru.putString("jenis_kelamin", jk);
                    Intent goInput = new Intent(menu_input_pertumbuhan.this, menu_hasil_rekomendasi_pertumbuhan.class);
                    startActivity(goInput);
                    CustomIntent.customType(menu_input_pertumbuhan.this, "fadein-to-fadeout");
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottom_dialog.dismiss();

                }
            });


            bottom_dialog.show();
        }else {
            Toast.makeText(this, "eror", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void hasil(String Status_perkembangan, String rekomendasi, String status, String jadwal_pertumbuhan, String jadwal_perkembangan,String kode_tindakan_perkembangan) {

    }
}
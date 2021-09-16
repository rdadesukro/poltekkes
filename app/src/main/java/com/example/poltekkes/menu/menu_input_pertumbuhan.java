package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import maes.tech.intentanim.CustomIntent;

public class menu_input_pertumbuhan extends AppCompatActivity {

    private Button btnSimpan;
    private EditText editBerat;
    private EditText editPanjang;
    BottomSheetDialog bottom_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_pertumbuhan);
        initView();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                validator.validate();
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
                ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close);
                pgl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottom_dialog.dismiss();
                    //    Toast.makeText(menu_input_pertumbuhan.this, "ade", Toast.LENGTH_SHORT).show();
//                        Guru.putString("tgl_lahir", ed.getText().toString().trim());
//                        Guru.putString("nama", editNama.getText().toString().trim());
//                        Guru.putString("alamat", editAlamat.getText().toString().trim());
                        // Guru.putString("berat", editBerat.getText().toString().trim());
                        // Guru.putString("panjang", editPanjang.getText().toString().trim());
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
            }
        });
    }

    private void initView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        editBerat = findViewById(R.id.edit_berat);
        editPanjang = findViewById(R.id.edit_panjang);
    }
}
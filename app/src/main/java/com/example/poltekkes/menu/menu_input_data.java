package com.example.poltekkes.menu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import maes.tech.intentanim.CustomIntent;

public class menu_input_data extends AppCompatActivity {

    private Button btnSimpan;
    private EditText editBerat;
    private EditText editPanjang;
    private EditText editNama;
    private EditText editTgl;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_data);
        initView();



        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editTgl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(menu_input_data.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guru.putString("tgl_lahir", editTgl.getText().toString().trim());
                Intent  goInput = new Intent(menu_input_data.this, menu_pertanyaan.class);
                startActivity(goInput);
                CustomIntent.customType(menu_input_data.this, "fadein-to-fadeout");
            }
        });
    }

    private void initView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        editBerat = findViewById(R.id.edit_berat);
        editPanjang = findViewById(R.id.edit_panjang);
        editNama = findViewById(R.id.edit_nama);
        editTgl = findViewById(R.id.edit_tgl);
    }
    private void updateLabel() {
        String myFormat = "MM-dd-yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTgl.setText(sdf.format(myCalendar.getTime()));
    }
}
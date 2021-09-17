package com.example.poltekkes.menu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.poltekkes.R;
import com.example.poltekkes.presenter.cek_umur;
import com.example.poltekkes.view.umur_view;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import maes.tech.intentanim.CustomIntent;

public class menu_input_data extends AppCompatActivity implements Validator.ValidationListener, umur_view {
    Validator validator;
    private Button btnSimpan;
    private EditText editBerat;
    private EditText editPanjang;
    private EditText editNama;
    private EditText editTgl;
    final Calendar myCalendar = Calendar.getInstance();
    BottomSheetDialog bottom_dialog;
    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_nama)
    EditText edit_nama;



    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_alamat)
    EditText edit_alamat;

    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_nama_ibu)
    EditText edit_nama_ibu;

    @NotEmpty(message = "tidak boleh kosong")
    @BindView(R.id.edit_tgl)
    EditText edit_tgl;



    @Checked(message = "Pilih Jenis Kelamin")
    @BindView(R.id.radio_grup)
    RadioGroup radio_grup;


    private ConstraintLayout conPertumbuhan;
    private ConstraintLayout conData;
    public Button btnSimpanData;
    private AppCompatEditText editAlamat;
    private RadioGroup radioGrup;
    private RadioButton rdLk;
    private RadioButton rdPr;
    String jk;
    private EditText editNamaIbu;

    cek_umur cek_umur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_data);
        initView();
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cek_umur = new cek_umur(this,menu_input_data.this);
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
        btnSimpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                validator.validate();

            }
        });

        radioGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rd_lk) {
                    jk = "L";

                } else if (checkedId == R.id.rd_pr) {
                    jk = "P";


                } else {
                    Toast.makeText(getApplicationContext(), "choice: Vibration",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void initView() {
        btnSimpan = findViewById(R.id.btn_simpan_data);
        editBerat = findViewById(R.id.edit_berat);
        editPanjang = findViewById(R.id.edit_panjang);
        editNama = findViewById(R.id.edit_nama);
        editTgl = findViewById(R.id.edit_tgl);
        conData = findViewById(R.id.con_data);
        btnSimpanData = findViewById(R.id.btn_simpan_data);
        editAlamat = findViewById(R.id.edit_alamat);
        radioGrup = findViewById(R.id.radio_grup);
        rdLk = findViewById(R.id.rd_lk);
        rdPr = findViewById(R.id.rd_pr);
        editNamaIbu = findViewById(R.id.edit_nama_ibu);
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTgl.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onValidationSucceeded() {
        cek_umur.get_umur(editTgl.getText().toString().trim());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void umur(String umur, String status,int bulan) {
        if (status.equals("1")){
            bottom_dialog = new BottomSheetDialog(menu_input_data.this);
            bottom_dialog.setTitle("Login");
            bottom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bottom_dialog.setContentView(R.layout.dialog_hasil_umur);
            bottom_dialog.setCancelable(false);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialog.getWindow().getAttributes());
            bottom_dialog.getWindow().setAttributes(lp);
            bottom_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            bottom_dialog.getWindow().setDimAmount(0.5f);
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


            Button pgl = (Button) bottom_dialog.findViewById(R.id.btn_pngggil);

            TextView hasil = (TextView) bottom_dialog.findViewById(R.id.txt_hasil);
            hasil.setText(umur);

            ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close);

            pgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottom_dialog.dismiss();
                    Toast.makeText(menu_input_data.this, "ade", Toast.LENGTH_SHORT).show();
                    Guru.putString("tgl_lahir", editTgl.getText().toString().trim());
                    Guru.putString("nama", editNama.getText().toString().trim());
                    Guru.putString("alamat", editAlamat.getText().toString().trim());
                    Guru.putString("jenis_kelamin", jk);
                    Guru.putString("nama_ibu", editNamaIbu.getText().toString().trim());
                    Guru.putString("bulan", String.valueOf(bulan));
                    Intent goInput = new Intent(menu_input_data.this, menu_input_pertumbuhan.class);
                    startActivity(goInput);
                    CustomIntent.customType(menu_input_data.this, "fadein-to-fadeout");
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

        }
    }
}
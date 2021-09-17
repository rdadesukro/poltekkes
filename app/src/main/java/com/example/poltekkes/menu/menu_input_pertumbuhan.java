package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maes.tech.intentanim.CustomIntent;

public class menu_input_pertumbuhan extends AppCompatActivity implements Validator.ValidationListener {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_input_pertumbuhan);
        initView();
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator.validate();
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
}
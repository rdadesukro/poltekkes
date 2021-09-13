package com.example.poltekkes.menu.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poltekkes.R;
import com.example.poltekkes.menu.presenter.aksi;
import com.github.squti.guru.Guru;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class menu_register  extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.btn_register)
    Button btnRegister;


    @NotEmpty(message = "Tidak Boleh Kosong")
    @ConfirmPassword(message = "Konfiramsi Password Tidak Sama")
    @BindView(R.id.edit_confirmasi)
    EditText editConfirmasi;

    @NotEmpty(message = "Tidak Boleh Kosong")
    @Password(min = 6, message = "Minimal Password 6 Karakter")
    @BindView(R.id.edit_password)
    EditText editPassword;


    Validator validator;
    ProgressDialog pd_new;

    @NotEmpty(message = "Tidak Boleh Kosong")
    @BindView(R.id.edit_nim)
    EditText editNik;

    @NotEmpty(message = "Tidak Boleh Kosong")
    @BindView(R.id.edit_nama)
    EditText edit_nama;


    @NotEmpty(message = "Tidak Boleh Kosong")
    @BindView(R.id.edit_username)
    EditText editusername;

    String email,nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_register);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        pd_new = new ProgressDialog(menu_register.this);
        Log.i("isi_server", "onCreate: "+ Guru.getString("server", "default value"));


    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {

        String password = editPassword.getText().toString().trim();
        String nik = editNik.getText().toString().trim();
        String username = editusername.getText().toString().trim();
        email = editNik.getText().toString().trim();
        nama = edit_nama.getText().toString().trim();

        Guru.putString("password", password);
        aksi countryPresenter = new aksi(null, menu_register.this);
        countryPresenter.register(email,username,nama,password,pd_new);
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
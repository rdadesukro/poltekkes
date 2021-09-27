package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.poltekkes.R;
import com.example.poltekkes.presenter.aksi;
import com.example.poltekkes.presenter.riset_password;
import com.example.poltekkes.view.riset_view;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;

public class menu_riset_password extends AppCompatActivity implements Validator.ValidationListener, riset_view {
    @BindView(R.id.btn_login)
    Button btnLogin;

    @NotEmpty
    @BindView(R.id.edit_username)
    EditText editUser;



    @NotEmpty
    @BindView(R.id.edit_nim)
    EditText edit_nim;
    riset_password riset_password;
    Validator validator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_riset_password);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        riset_password = new riset_password(this,menu_riset_password.this);
    }


    @Override
    public void onValidationSucceeded() {

        riset_password.riset(edit_nim.getText().toString().trim(),editUser.getText().toString().trim());
    }
    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                validator.validate();
                break;

        }
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
    public void riset(String status, String pesan) {
        if (status.equals("1")){
            masih_kosong(pesan);
        }else {
            Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();
        }

    }
    void masih_kosong(String pesan){

        SweetAlertDialog pDialog = new SweetAlertDialog(menu_riset_password.this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("Berhasil");
        pDialog.setContentText(pesan);
        pDialog.setCancelable(false);
        pDialog.setConfirmText("OK");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent goInput = new Intent(menu_riset_password.this, menu_login.class);
                startActivity(goInput);
                CustomIntent.customType(menu_riset_password.this, "fadein-to-fadeout");
            }
        });

        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
}
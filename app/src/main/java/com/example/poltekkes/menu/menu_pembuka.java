package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.poltekkes.R;
import com.github.squti.guru.Guru;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

public class menu_pembuka extends AppCompatActivity {

    String status_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_pembuka);
        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(getApplicationContext());
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        getSupportActionBar().hide();
        status_login = Guru.getString("status_loign", "false");
        star();
    }

    void star(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (status_login.equals("false")){
                    Intent intent  = new Intent(menu_pembuka.this, menu_login.class);
                    startActivity(intent);
                }else {

                    Intent intent  = new Intent(menu_pembuka.this, menu_utama.class);
                    startActivity(intent);
                }

            }
        }, 3000L); //3000 L = 3 detik
    }
}
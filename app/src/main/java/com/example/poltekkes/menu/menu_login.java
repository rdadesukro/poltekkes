package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.poltekkes.R;

public class menu_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_login);
        getSupportActionBar().hide();
    }
}
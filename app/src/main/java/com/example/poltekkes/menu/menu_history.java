package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.poltekkes.R;

public class menu_history extends AppCompatActivity {

    String umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_history);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_umur,menu);
        MenuItem bln_3 = menu.findItem(R.id.bln_3);
        MenuItem blm_9 = menu.findItem(R.id.blm_9);
        MenuItem blm_12 = menu.findItem(R.id.blm_12);
        MenuItem blm_18 = menu.findItem(R.id.blm_18);
        MenuItem blm_24 = menu.findItem(R.id.blm_24);
        MenuItem blm_30 = menu.findItem(R.id.blm_30);
        MenuItem blm_36 = menu.findItem(R.id.blm_36);
        MenuItem blm_42 = menu.findItem(R.id.blm_42);
        MenuItem blm_48 = menu.findItem(R.id.blm_48);
        MenuItem blm_54 = menu.findItem(R.id.blm_54);
        MenuItem blm_60 = menu.findItem(R.id.blm_60);
        bln_3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
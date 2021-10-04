package com.example.poltekkes.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.poltekkes.R;
import com.example.poltekkes.model.history.DataItem_history;
import com.example.poltekkes.presenter.history;
import com.example.poltekkes.view.history_view;
import com.github.squti.guru.Guru;

import java.util.List;

public class menu_rekap extends AppCompatActivity implements history_view {

    private WebView webData;
    com.example.poltekkes.presenter.history history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_rekap);
        initView();
        history = new history(this, menu_rekap.this);
        history.get_history("xxx");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void history(List<DataItem_history> history) {
        String[] myTable = new String[history.size()];
        Log.i("Data_history", "history: "+history);
        for (int i = 0; i < history.size(); i++) {

             myTable[i] = "<table border=1>" +
                    "<tr>" +
                    "<td>Nama Balita</td>" +
                    "<td>:</td>" +
                    "<td>"+history.get(i).getNamaBalita()+"</td>" +
                    "</tr>"+
                    "</table>";
            webData.requestFocus();
            Log.i("data_table", "history: "+myTable);
            webData.getSettings().setLightTouchEnabled(true);
            webData.getSettings().setJavaScriptEnabled(true);
            webData.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            webData.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webData.loadDataWithBaseURL(null, myTable[i], "text/html", "utf-8", null);
        }

        // webDataBayi.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ text, "text/html", "UTF-8", "");

    }

    private void initView() {
        webData = findViewById(R.id.web_data);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
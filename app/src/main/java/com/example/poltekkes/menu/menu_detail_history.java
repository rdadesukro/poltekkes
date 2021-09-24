package com.example.poltekkes.menu;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_detail_history;
import com.example.poltekkes.model.detail_history.JawabanItem;
import com.example.poltekkes.presenter.history_detail;
import com.example.poltekkes.view.history_detail_view;
import com.github.squti.guru.Guru;

import java.util.List;

public class menu_detail_history extends AppCompatActivity implements history_detail_view {
    private com.example.poltekkes.adapter.adapter_detail_history adapter_detail_history;
    private androidx.recyclerview.widget.RecyclerView RecyclerView;
    private ProgressBar progressBar;
    com.example.poltekkes.presenter.history_detail history_detail;
    String id, nama, tgl_lahir, alamat, bb, pb, nama_ibu, jenis_kelamin;
    public String data;
    private TextView webDataBayi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail_history);
        initView();
        data = "<h2>Pengenalan Aplikasi</h2>\n" +
                "<ul>\n" +
                "    <li>Nama Balita         : "+ nama +"</li>"+
                "    <li>Tangal Lahir        : "+ tgl_lahir +"</li>"+
                "    <li>Jenis Kelamin : " + jenis_kelamin +"</li>"+
                "    <li>Berat Badan      : "+ bb +"</li>"+
                "    <li>Panjang Badan   : "+ pb +"</li>"+
                "    <li>Alamat          :</li>" + alamat +
                "    <li>Nama Ibu        :</li>" + nama_ibu +
                "</ul>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            webDataBayi.setText(Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY));
        } else
            webDataBayi.setText(Html.fromHtml(data));
        id = Guru.getString("id_detail_histiry", "false");
        history_detail = new history_detail(this, menu_detail_history.this);
        history_detail.get_history_detail(id);
    }

    @Override
    public void history_detail(List<JawabanItem> history_detail) {
        try {
            Log.i("data_history", "history: " + history_detail.size());

            adapter_detail_history = new adapter_detail_history(menu_detail_history.this, history_detail, 1, null);
            RecyclerView.setLayoutManager(new LinearLayoutManager(menu_detail_history.this, LinearLayoutManager.VERTICAL, false));
            RecyclerView.setHasFixedSize(true);
            adapter_detail_history.notifyDataSetChanged();
            RecyclerView.setAdapter(adapter_detail_history);
            //swifeRefresh.setRefreshing(false);
            if (history_detail.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Log.i("cek_data_history", "history: " + e.getMessage());

        }
    }

    private void initView() {
        RecyclerView = findViewById(R.id.RecyclerView);
        progressBar = findViewById(R.id.progressBar);
        webDataBayi = (TextView) findViewById(R.id.web_data_bayi);
    }
}
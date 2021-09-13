package com.example.poltekkes.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_pertanyaan;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.presenter.pertanyaan;
import com.example.poltekkes.view.pertanyaan_view;
import com.github.squti.guru.Guru;

import java.util.List;

public class menu_pertanyaan extends AppCompatActivity implements pertanyaan_view, adapter_pertanyaan.OnImageClickListener {

    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar2;
    private  adapter_pertanyaan adapter_pertanyaan;
    com.example.poltekkes.presenter.pertanyaan pertanyaan;
    String tgl_lahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_pertanyaan);
        initView();
        pertanyaan = new pertanyaan(this,menu_pertanyaan.this);
        tgl_lahir =  Guru.getString("tgl_lahir", "false");
        pertanyaan.get_pertanyan(tgl_lahir);

    }

    private void initView() {
        swifeRefresh = findViewById(R.id.swifeRefresh);
        rvAku = findViewById(R.id.rv_aku);
        progressBar2 = findViewById(R.id.progressBar2);
    }

    @Override
    public void onImageClick(int id, String nama, String alamat) {

    }

    @Override
    public void pertanyaan(List<DataItem_pertanyaan> pertanyaan) {
        try {
            //  progKes.setVisibility(View.VISIBLE);
            Log.i("cek_data_pertanyaan", "event: " + pertanyaan.size());
            adapter_pertanyaan = new adapter_pertanyaan(menu_pertanyaan.this, pertanyaan, 1, this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_pertanyaan.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setAdapter(adapter_pertanyaan);
            swifeRefresh.setRefreshing(false);
            if (pertanyaan.size() == 0) {
                progressBar2.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                progressBar2.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void status(String status, String pesan) {

    }
}
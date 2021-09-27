package com.example.poltekkes.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_history;
import com.example.poltekkes.adapter.adapter_pertanyaan;
import com.example.poltekkes.model.history.DataItem_history;
import com.example.poltekkes.presenter.history;
import com.example.poltekkes.view.history_view;
import com.github.squti.guru.Guru;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

public class menu_history extends AppCompatActivity implements history_view, adapter_pertanyaan.OnImageClickListener {

    String umur;
    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private com.example.poltekkes.adapter.adapter_history adapter_history;
    com.example.poltekkes.presenter.history history;
    private ProgressBar progressBar4;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_history);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        username = Guru.getString("username", "false");
        history = new history(this, menu_history.this);
        history.get_history(username);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_umur, menu);
//        MenuItem bln_3 = menu.findItem(R.id.bln_3);
//        MenuItem blm_9 = menu.findItem(R.id.blm_9);
//        MenuItem blm_12 = menu.findItem(R.id.blm_12);
//        MenuItem blm_18 = menu.findItem(R.id.blm_18);
//        MenuItem blm_24 = menu.findItem(R.id.blm_24);
//        MenuItem blm_30 = menu.findItem(R.id.blm_30);
//        MenuItem blm_36 = menu.findItem(R.id.blm_36);
//        MenuItem blm_42 = menu.findItem(R.id.blm_42);
//        MenuItem blm_48 = menu.findItem(R.id.blm_48);
//        MenuItem blm_54 = menu.findItem(R.id.blm_54);
//        MenuItem blm_60 = menu.findItem(R.id.blm_60);
//        bln_3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void history(List<DataItem_history> history) {
        try {
            Log.i("data_history", "history: " + history.size());

            adapter_history = new adapter_history(menu_history.this, history, 1,this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_history.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setHasFixedSize(true);
            adapter_history.notifyDataSetChanged();
            rvAku.setAdapter(adapter_history);
            swifeRefresh.setRefreshing(false);
            if (history.size() == 0) {
                progressBar4.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                progressBar4.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {
            Log.i("cek_data_history", "history: "+e.getMessage());

        }
    }

    private void initView() {
        swifeRefresh = findViewById(R.id.swifeRefresh);
        rvAku = findViewById(R.id.rv_aku);
        progressBar4 = findViewById(R.id.progressBar4);
    }


    @Override
    public void onImageClick(int id, String nama, String alamat) {

    }

    @Override
    public void lihat_gambar(int id, String gambar, ImageView foto) {

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
}
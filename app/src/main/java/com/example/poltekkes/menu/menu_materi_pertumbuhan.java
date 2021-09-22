package com.example.poltekkes.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_materi;
import com.example.poltekkes.model.materi.DataItem_materi;
import com.example.poltekkes.presenter.materi;
import com.example.poltekkes.view.materi_view;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.SSLContext;

public class menu_materi_pertumbuhan extends AppCompatActivity implements materi_view, adapter_materi.OnImageClickListener  {


    private WebView web;
    private ProgressBar progressBar3;
    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    com.example.poltekkes.presenter.materi materi;
    private com.example.poltekkes.adapter.adapter_materi adapter_materi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_materi_pertumbuhan);
        initView();
        materi = new materi(this,menu_materi_pertumbuhan.this);
        materi.get_materi("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        try {
            // Google Play will install latest OpenSSL
            ProviderInstaller.installIfNeeded(this);
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
            sslContext.createSSLEngine();
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException
                | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        web.setWebViewClient(new menu_materi_pertumbuhan.myWebclient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=https://ppid.jambikota.go.id/files/dokumen_16_ERAQC.pdf");

    }

    private void initView() {
        web = findViewById(R.id.web);
        progressBar3 = findViewById(R.id.progressBar3);
        swifeRefresh = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        rvAku = (RecyclerView) findViewById(R.id.rv_aku);
    }

    @Override
    public void materi(List<DataItem_materi> materi) {
        try {

            Log.i("isi_jawaban", "pertanyaan: "+materi);
            Log.i("cek_data_pertanyaan", "event: " + materi.size());
            adapter_materi = new adapter_materi(menu_materi_pertumbuhan.this, materi, 1, this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_materi_pertumbuhan.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setAdapter(adapter_materi);
            swifeRefresh.setRefreshing(false);
            if (materi.size() == 0) {
                // progressBar2.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                // progressBar2.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onImageClick(int id, String nama, String alamat) {

    }

    public class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar3.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
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
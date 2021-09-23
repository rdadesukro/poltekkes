package com.example.poltekkes.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_pertanyaan;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.presenter.pertanyaan;
import com.example.poltekkes.presenter.rekomendasi;
import com.example.poltekkes.view.pertanyaan_view;
import com.example.poltekkes.view.rekomendasi_view;
import com.github.squti.guru.Guru;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import maes.tech.intentanim.CustomIntent;

public class menu_pertanyaan extends AppCompatActivity implements rekomendasi_view,pertanyaan_view, adapter_pertanyaan.OnImageClickListener {

    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar2;
    private adapter_pertanyaan adapter_pertanyaan;
    com.example.poltekkes.presenter.pertanyaan pertanyaan;
    com.example.poltekkes.presenter.rekomendasi rekomendasi;
    String tgl_lahir,nama,berat,panjang,rentang_usia;
    private TextView txtDataAnak;
    private Button btnSimpan;
    BottomSheetDialog bottom_dialog;
    public static List<String> jawaban = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_pertanyaan);
        initView();
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pertanyaan = new pertanyaan(this, menu_pertanyaan.this);
        rekomendasi = new rekomendasi(this, menu_pertanyaan.this);
        tgl_lahir = Guru.getString("tgl_lahir", "false");
        berat = Guru.getString("berat", "false");
        panjang = Guru.getString("panjang", "false");
        nama = Guru.getString("nama", "false");
        Log.i("tgl_lahir", "onCreate: " + tgl_lahir);
        pertanyaan.get_pertanyan(tgl_lahir);
        txtDataAnak.setText("Nama anak "+nama+" dengan umur "+rentang_usia+","+" berat "+berat+" dan panjang "+panjang );

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rekomendasi.get_tindakan(tgl_lahir, String.valueOf(jawaban));
            }
        });

        swifeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pertanyaan.get_pertanyan(tgl_lahir);

            }
        });

    }

    private void initView() {
        swifeRefresh = findViewById(R.id.swifeRefresh);
        rvAku = findViewById(R.id.rv_aku);
        progressBar2 = findViewById(R.id.progressBar2);
        txtDataAnak = findViewById(R.id.txt_data_anak);
        btnSimpan = findViewById(R.id.btn_simpan_data);
    }

    @Override
    public void onImageClick(int id, String nama, String alamat) {
        jawaban.set(id,nama);

    }

    @Override
    public void pertanyaan(List<DataItem_pertanyaan> pertanyaan) {
        try {
            String s1 ='"'+"tidak"+'"';
            String s2 ='"'+"judul"+'"';
            for (int i = 0; i < pertanyaan.size(); i++) {
                String first = pertanyaan.get(i).getText();
                String s=first.substring(0,1);

                if (s.equals("#")){
                    jawaban.add(s2);
                }else {
                    jawaban.add(s1);
                }

            }
            Log.i("isi_jawaban", "pertanyaan: "+jawaban);
            Log.i("cek_data_pertanyaan", "event: " + pertanyaan.size());
            adapter_pertanyaan = new adapter_pertanyaan(menu_pertanyaan.this, pertanyaan, 1, this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_pertanyaan.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setHasFixedSize(true);
            adapter_pertanyaan.notifyDataSetChanged();
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

    @Override
    public void rentang_usia(String rentang_usi) {
        rentang_usi=rentang_usia;
        Log.i("rentang_usia", "rentang_usia: "+rentang_usi);

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

    @Override
    public void umur(String Status_pertumbuhan, String rekomendasi, String status) {
        if (status.equals("1")){
            bottom_dialog = new BottomSheetDialog(menu_pertanyaan.this);
            bottom_dialog.setTitle("Login");
            bottom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            bottom_dialog.setContentView(R.layout.dialog_hasil);
            bottom_dialog.setCancelable(false);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialog.getWindow().getAttributes());
            bottom_dialog.getWindow().setAttributes(lp);
            bottom_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            bottom_dialog.getWindow().setDimAmount(0.5f);
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


            Button pgl = (Button) bottom_dialog.findViewById(R.id.btn_pngggil);
            WebView hasil = (WebView) bottom_dialog.findViewById(R.id.txt_hasil);
            hasil.requestFocus();
            hasil.getSettings().setLightTouchEnabled(true);
            hasil.getSettings().setJavaScriptEnabled(true);
            hasil.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            hasil.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            hasil.loadDataWithBaseURL("","<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ Status_pertumbuhan, "text/html", "UTF-8", "");

            ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close2);
            pgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bottom_dialog.dismiss();
                    Guru.putString("tindakan",rekomendasi);bottom_dialog.dismiss();
                    Intent goInput = new Intent(menu_pertanyaan.this, menu_hasil_tindakaan.class);
                    startActivity(goInput);
                    CustomIntent.customType(menu_pertanyaan.this, "fadein-to-fadeout");
                }
            });
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottom_dialog.dismiss();

                }
            });


            bottom_dialog.show();
        }else {

        }
    }
}
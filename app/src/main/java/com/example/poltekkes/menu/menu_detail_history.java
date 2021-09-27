package com.example.poltekkes.menu;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.poltekkes.R;
import com.example.poltekkes.adapter.adapter_detail_history;
import com.example.poltekkes.model.detail_history.Balita;
import com.example.poltekkes.model.detail_history.JawabanItem;
import com.example.poltekkes.model.detail_history.Pemeriksa;
import com.example.poltekkes.model.detail_history.Perkembangan;
import com.example.poltekkes.model.detail_history.Pertumbuhan;
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
    private TextView txtNama;
    private TextView txtNim;
    private WebView webRekPertumbuhan;
    private WebView txtStatus;
    private WebView txtWaktuPertumbuhan;
    private WebView webRekPerkembangan;
    private WebView txtStatusPerke;
    private WebView txtWaktuPerkembangan;
    private Button btnBalita;
    private ConstraintLayout conBalita;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail_history);
        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        id = Guru.getString("id_detail_histiry", "false");
        history_detail = new history_detail(this, menu_detail_history.this);
        history_detail.get_history_detail(id);

        // txtNama.setText(nama);
        btnBalita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conBalita.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(conBalita, new AutoTransition());
                    conBalita.setVisibility(View.VISIBLE);
                    btnBalita.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);

                } else {
                    TransitionManager.beginDelayedTransition(conBalita, new AutoTransition());
                    conBalita.setVisibility(View.GONE);
                    btnBalita.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    btnBalita.setBackgroundResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }
            }
        });

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

    @Override
    public void data_bayi(String nama_bayi, String tgl_lahir, String bb, String pb, String jk, String alamat, String nama_ibu) {
        nama = nama_bayi;
        Log.i("nama_bayi", "data_bayi: " + nama_bayi);
        //txtNama.setText(nama_bayi);
    }

    @Override
    public void pemeriksa(Pemeriksa pemeriksa) {
        txtNama.setText(pemeriksa.getNamaLengkap());
        txtNim.setText(pemeriksa.getNim());
    }

    @Override
    public void pertumbuhan(Pertumbuhan pertumbuhan) {


        txtStatus.requestFocus();
        txtStatus.getSettings().setLightTouchEnabled(true);
        txtStatus.getSettings().setJavaScriptEnabled(true);
        txtStatus.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        txtStatus.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        txtStatus.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + pertumbuhan.getStatusPertumbuhan(), "text/html", "UTF-8", "");


        webRekPertumbuhan.requestFocus();
        webRekPertumbuhan.getSettings().setLightTouchEnabled(true);
        webRekPertumbuhan.getSettings().setJavaScriptEnabled(true);
        webRekPertumbuhan.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webRekPertumbuhan.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webRekPertumbuhan.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + pertumbuhan.getStatusRekomendasi(), "text/html", "UTF-8", "");

    }

    @Override
    public void perkembangan(Perkembangan perkembangan) {

        webRekPerkembangan.requestFocus();
        webRekPerkembangan.getSettings().setLightTouchEnabled(true);
        webRekPerkembangan.getSettings().setJavaScriptEnabled(true);
        webRekPerkembangan.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webRekPerkembangan.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webRekPerkembangan.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + perkembangan.getTindakan(), "text/html", "UTF-8", "");
        txtWaktuPertumbuhan.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + perkembangan.getJadwalPertumbuhan(), "text/html", "UTF-8", "");
        txtWaktuPerkembangan.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + perkembangan.getJadwalPerkembangan(), "text/html", "UTF-8", "");
        txtStatusPerke.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + perkembangan.getHasil(), "text/html", "UTF-8", "");


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void balita(Balita balita) {
        String jk;
        if (balita.getJenisKelamin().equals("L")) {
            jk = "Laki-Laki";
        } else {
            jk = "Perempuan";
        }


        data = "<ul>\n" +
                "<li><strong>Nama Balita</strong></li>\n" + "&nbsp;" + balita.getNama() +
                "<li><strong>Tanggal Lahir</strong></li>\n" + "&nbsp;" + balita.getTanggalLahir() +
                "<li><strong>Umur</strong></li>\n" + "&nbsp;" + balita.getUsia() +
                "<li><strong>Jenis Kelamin </strong></li>\n" + "&nbsp;" + jk +
                "<li><strong>Berat Badan</strong></li>\n" + "&nbsp;" + balita.getBerat() +
                "<li><strong>Panjang Badan</strong></li>\n" + "&nbsp;" + balita.getPanjang() +
                "<li><strong>Alamat </strong></li>\n" + "&nbsp;" + balita.getAlamat() +
                "<li><strong>Nama Ibu </strong></li>\n" + "&nbsp;" + balita.getNamaIbu() +
                "</ul>";
        webDataBayi.setText(Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY));
    }

    private void initView() {
        RecyclerView = findViewById(R.id.RecyclerView);
        progressBar = findViewById(R.id.progressBar);
        webDataBayi = (TextView) findViewById(R.id.web_data_bayi);
        txtNama = (TextView) findViewById(R.id.txt_nama);
        txtNim = (TextView) findViewById(R.id.txt_nim);
        webRekPertumbuhan = (WebView) findViewById(R.id.web_rek_pertumbuhan);
        txtStatus = (WebView) findViewById(R.id.txt_status);
        txtWaktuPertumbuhan = (WebView) findViewById(R.id.txt_waktu_pertumbuhan);
        webRekPerkembangan = (WebView) findViewById(R.id.web_rek_perkembangan);
        txtStatusPerke = (WebView) findViewById(R.id.txt_status_perke);
        txtWaktuPerkembangan = (WebView) findViewById(R.id.txt_waktu_perkembangan);
        btnBalita = findViewById(R.id.btn_balita);
        conBalita = findViewById(R.id.con_balita);
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
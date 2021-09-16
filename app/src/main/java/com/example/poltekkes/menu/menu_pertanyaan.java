package com.example.poltekkes.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class menu_pertanyaan extends AppCompatActivity implements pertanyaan_view, adapter_pertanyaan.OnImageClickListener {

    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar2;
    private adapter_pertanyaan adapter_pertanyaan;
    com.example.poltekkes.presenter.pertanyaan pertanyaan;
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
        pertanyaan = new pertanyaan(this, menu_pertanyaan.this);
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
//                pertanyaan.simpan_pertanyaan(String.valueOf(jawaban),rentang_usia,nama,berat,panjang);
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
                ImageView close = (ImageView) bottom_dialog.findViewById(R.id.btn_close2);
                pgl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottom_dialog.dismiss();

                    }
                });
                bottom_dialog.show();

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
            String s1 ="tidak";
            for (int i = 0; i < pertanyaan.size(); i++) {
                jawaban.add(s1);
            }
            Log.i("isi_jawaban", "pertanyaan: "+jawaban);
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

            pgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            bottom_dialog.show();
        }else {

        }

    }

    @Override
    public void rentang_usia(String rentang_usi) {
        rentang_usi=rentang_usia;
        Log.i("rentang_usia", "rentang_usia: "+rentang_usi);

    }
}
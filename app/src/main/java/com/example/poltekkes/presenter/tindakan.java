package com.example.poltekkes.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.poltekkes.model.rekomendasi.Response_rekomendasi;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.rekomendasi_view;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class tindakan {

    private Context ctx;
    private rekomendasi_view countryView;
    private Retroserver_server_AUTH countryService;
    public tindakan(rekomendasi_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }

    public void get_rekomendasi(String berat,String jk,String usia) {
        ProgressDialog pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Simpan Data...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());
        Call<Response_rekomendasi> call = api.get_rekomendasi(berat,jk,usia);

        call.enqueue(new Callback<Response_rekomendasi>() {
            @Override
            public void onResponse(Call<Response_rekomendasi> call, Response<Response_rekomendasi> response) {

                try {

                    if (response.body().isSuccess()) {
                        pDialog.dismiss();
                        Response_rekomendasi data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //Log.i("isi_data", "onResponse: "+data);

                            countryView.umur(response.body().getData().getStatusPertumbuhan(),
                                    response.body().getData().getRekomendasi(),"1");

                    }
                } catch (Exception e) {
                    Log.i("onResponse", "There is an error" + e.getMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_rekomendasi> call, Throwable t) {
                t.printStackTrace();
                Log.i("cek_error", "onFailure: " + t);
                if (t instanceof IOException) {

                    Log.i("cek_error", "onFailure: " + t);
                } else {

                    Log.i("cek_error", "onFailure: " + t);
                }
            }
        });
    }



    }



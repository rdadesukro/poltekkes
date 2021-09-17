package com.example.poltekkes.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.poltekkes.model.action.Response_action;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.model.umur.Response_balita;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.pertanyaan_view;
import com.example.poltekkes.view.umur_view;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class cek_umur {

    private Context ctx;
    private umur_view countryView;
    private Retroserver_server_AUTH countryService;
    public cek_umur(umur_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }

    public void get_umur(String tanggal_lahir) {
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());
        Call<Response_balita> call = api.get_umur(tanggal_lahir);

        call.enqueue(new Callback<Response_balita>() {
            @Override
            public void onResponse(Call<Response_balita> call, Response<Response_balita> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_balita data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("isi_data", "onResponse: "+data);
                        if (data != null && data.getBalita() != null) {
                            countryView.umur(response.body().getBalita().getUsiaTerbilang(),"1",response.body().getBalita().getUsiaDalamBulan());
                        }
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_balita> call, Throwable t) {
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



package com.example.poltekkes.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.poltekkes.model.action.Response_action;
import com.example.poltekkes.model.materi.DataItem_materi;
import com.example.poltekkes.model.materi.Response_materi;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.server.Retroserver_server_materi;
import com.example.poltekkes.view.materi_view;
import com.example.poltekkes.view.pertanyaan_view;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class materi {

    private Context ctx;
    private materi_view countryView;
    private Retroserver_server_materi countryService;
    public materi(materi_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_materi();
        }
    }

    public void get_materi(String tanggal_lahir) {
        ApiRequest api = Retroserver_server_materi.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_materi.getClient().baseUrl());
        Call<Response_materi> call = api.get_materi();
        call.enqueue(new Callback<Response_materi>() {
            @Override
            public void onResponse(Call<Response_materi> call, Response<Response_materi> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_materi data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("isi_data", "onResponse: "+data);
                        if (data != null && data.getData() != null) {
                            List<DataItem_materi> result = data.getData();
                            countryView.materi(result);
                        }
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_materi> call, Throwable t) {
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



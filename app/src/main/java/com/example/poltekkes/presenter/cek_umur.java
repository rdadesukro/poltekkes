package com.example.poltekkes.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.poltekkes.model.umur.Response_umur;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.umur_view;

import java.io.IOException;

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
        ProgressDialog  pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Simpan Data...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());
        Call<Response_umur> call = api.get_umur(tanggal_lahir);

        call.enqueue(new Callback<Response_umur>() {
            @Override
            public void onResponse(Call<Response_umur> call, Response<Response_umur> response) {

                try {

                    if (response.body().isSuccess()) {
                        pDialog.dismiss();
                        Response_umur data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                      //  Log.i("isi_data", "onResponse: "+response.body().getBalita().getUsiaTerbilang());

                            countryView.umur(response.body().getData().getBalita().getUsiaTerbilang(),"1",response.body().getData().getBalita().getUsiaDalamBulan());
                    }else {
                        pDialog.dismiss();
                        Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } catch (Exception e) {
                    Log.i("onResponse", "There is an error" + e.getMessage());
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_umur> call, Throwable t) {
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



package com.example.poltekkes.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
;
import com.example.poltekkes.model.action.Response_simpan_data;
import com.example.poltekkes.model.login.Response_login;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.pertanyaan_view;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pertanyaan {

    private Context ctx;
    private pertanyaan_view countryView;
    private Retroserver_server_AUTH countryService;
    public pertanyaan(pertanyaan_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }

    public void get_pertanyan(String tanggal_lahir) {
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());
        Call<Response_pertanyaan> call = api.get_pertanyaan(tanggal_lahir);

        call.enqueue(new Callback<Response_pertanyaan>() {
            @Override
            public void onResponse(Call<Response_pertanyaan> call, Response<Response_pertanyaan> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_pertanyaan data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("isi_data", "onResponse: "+data);
                        if (data != null && data.getData() != null) {
                            List<DataItem_pertanyaan> result = data.getData();
                            countryView.pertanyaan(result);
                            //countryView.rentang_usia(response.body().getRentang_usia_bayi());
                        }
                    }
                } catch (Exception e) {
                    Log.i("eror_pertanyaan", "onResponse: "+e.getMessage());
                    Log.e("onResponse", "There is an error" + e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_pertanyaan> call, Throwable t) {
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

    public void simpan_pertanyaan(String nama,
                                  String tgl_lahir,
                                  String nama_ibu,
                                  String alamat,
                                  String usia_dalam_bulan,
                                  String jenis_kelamin,
                                  String berat_badan,
                                  String panjang_badan,
                                  String kode_pertumbuhan,
                                  String kode_rekomendasi,
                                  String kode_tindakan_perkembangan,
                                  String jawaban_array) {
        ProgressDialog  pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Simpan Data...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        Log.i("cek_semua_Data", "simpan_pertanyaan: "+nama+" "+
                tgl_lahir +" "+
                nama_ibu+ " "+
                alamat+" "+
                usia_dalam_bulan+ " "+
                jenis_kelamin + " "+
                berat_badan +  " "+
                panjang_badan +" "+
                kode_pertumbuhan + " "+
                kode_rekomendasi+" "+
               kode_tindakan_perkembangan+
            jawaban_array);

        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_simpan_data> sendbio = api.simpan_semua_data(nama,
                                                            tgl_lahir,
                nama_ibu,
                alamat,
                usia_dalam_bulan,
                jenis_kelamin,
                berat_badan,
                panjang_badan,
                kode_pertumbuhan,
                kode_rekomendasi,
                kode_tindakan_perkembangan,
                jawaban_array);
        sendbio.enqueue(new Callback<Response_simpan_data>() {
            @Override
            public void onResponse(Call<Response_simpan_data> call, Response<Response_simpan_data> response) {

                try {


                    if (response.body().isSuccess()) {
                        finalPDialog.dismiss();
                        countryView.status("1","");
                      //  new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();

                    } else {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                    }
                }catch (Exception e){
                  //  Toast.makeText(ctx, ""+response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_simpan_data> call, Throwable t) {
                Log.i("cek_info", "onFailure: "+t);
                Log.e("cek_eror_login", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }


    }



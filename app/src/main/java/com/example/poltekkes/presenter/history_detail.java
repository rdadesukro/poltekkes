package com.example.poltekkes.presenter;

import android.content.Context;
import android.util.Log;

import com.example.poltekkes.model.detail_history.Balita;
import com.example.poltekkes.model.detail_history.Data;
import com.example.poltekkes.model.detail_history.JawabanItem;
import com.example.poltekkes.model.detail_history.Pemeriksa;
import com.example.poltekkes.model.detail_history.Perkembangan;
import com.example.poltekkes.model.detail_history.Pertumbuhan;
import com.example.poltekkes.model.detail_history.Response_detail_history;
import com.example.poltekkes.model.history.DataItem_history;
import com.example.poltekkes.model.history.Response_history;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.history_detail_view;
import com.example.poltekkes.view.history_view;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class history_detail {

    private Context ctx;
    private history_detail_view countryView;
    private Retroserver_server_AUTH countryService;
    public history_detail(history_detail_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }

    public void get_history_detail(String id) {
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());

        Call<Response_detail_history> call = api.get_history_detail(id);



        call.enqueue(new Callback<Response_detail_history>() {
            @Override
            public void onResponse(Call<Response_detail_history> call, Response<Response_detail_history> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_detail_history data = response.body();
                        Balita balita =  data.getData().getBalita();
                        Perkembangan perkembanga = data.getData().getPerkembangan();
                        Pertumbuhan pertumbuhan = data.getData().getPertumbuhan();
                        Pemeriksa pemeriksa = data.getData().getPemeriksa();


                        countryView.balita(balita);
                        countryView.pemeriksa(pemeriksa);
                        countryView.perkembangan(perkembanga);
                        countryView.pertumbuhan(pertumbuhan);


                        Log.i("isi_data", "onResponse: "+data);
                        if (data != null && data.getData() != null) {

                            List<JawabanItem> result = data.getData().getJawaban();
                            countryView.history_detail(result);
                            countryView.balita(balita);
                        }
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_detail_history> call, Throwable t) {
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

//    public void simpan_pertanyaan(String jawaban,String usia) {
//        ProgressDialog  pDialog = new ProgressDialog(ctx);
//        pDialog.setMessage("Simpan Jawaban...");
//        pDialog.setCancelable(false);
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//        ProgressDialog finalPDialog = pDialog;
//
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//        Call<Response_action> sendbio = api.simpan_pertanyaan(na,usia);
//        sendbio.enqueue(new Callback<Response_action>() {
//            @Override
//            public void onResponse(Call<Response_action> call, Response<Response_action> response) {
//
//                try {
//                    String kode = response.body().getKode();
//                    countryView.status(kode,"");
//                    if (kode.equals("1")) {
//                        finalPDialog.dismiss();
//
//                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
//
//                    } else {
//                        finalPDialog.dismiss();
//                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
//
//                    }
//                }catch (Exception e){
//                    Log.i("cek_error_login", "onResponse: "+e);
//                    finalPDialog.dismiss();
//                }
//
//
//
//            }
//            @Override
//            public void onFailure(Call<Response_action> call, Throwable t) {
//                Log.i("cek_info", "onFailure: "+t);
//                Log.e("cek_eror_login", "onFailure: "+t);
//
//                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
//            }
//        });
//
//    }
//    public  void  hapus_optik(String id, ProgressDialog pDialog ){
//        pDialog = new ProgressDialog(ctx);
//        pDialog.setTitle("Mohon Tunggu!!!");
//        pDialog.setMessage("Hapus Data");
//        pDialog.setCancelable(false);
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//        ProgressDialog finalPDialog = pDialog;
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//
//        Call<Response_action> sendbio = api.hapus_optik(id);
//
//
//        sendbio.enqueue(new Callback<Response_action>() {
//            @Override
//            public void onResponse(Call<Response_action> call, Response<Response_action> response) {
//
//                String kode = response.body().getKode();
//                Log.i("kode_foto", "onResponse: " + kode);
//                countryView.status(kode,"");
//
//                if (kode.equals("1")) {
//                    finalPDialog.dismiss();
//                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
//
//                } else {
//
//                    finalPDialog.dismiss();
//                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
//                }
//
//            }
//            @Override
//            public void onFailure(Call<Response_action> call, Throwable t) {
//                Log.i("cek_error", "onFailure: "+t);
//
//                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
//            }
//        });
//
//    }

    }



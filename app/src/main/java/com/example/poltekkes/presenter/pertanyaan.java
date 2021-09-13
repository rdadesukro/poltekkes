package com.example.poltekkes.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
;
import com.example.poltekkes.model.pertanyaan.DataItem_pertanyaan;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.pertanyaan_view;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                        }
                    }
                } catch (Exception e) {
                    Log.e("onResponse", "There is an error" + e);
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Response_pertanyaan> call, Throwable t) {
                t.printStackTrace();
                Log.i("cek_error", "onFailure: " + t);
                if (t instanceof IOException) {


                } else {


                }
            }
        });
    }

//    public void simpan_optik(SweetAlertDialog pDialog,
//                                RequestBody id,
//                                RequestBody nama,
//                                RequestBody alamat,
//                                RequestBody phone,
//                                RequestBody lat,
//                                RequestBody lng,
//                                RequestBody status,
//                                String jenis,
//                                RequestBody status_bpjs,
//                                RequestBody asu,
//                                RequestBody informasi,
//                                RequestBody jam,
//                                MultipartBody.Part foto) {
//        pDialog = new SweetAlertDialog((Activity) ctx, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
//        pDialog.setTitleText("Simpan Data");
//        pDialog.setContentText("Mohon tunggu sedang memproses...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//
//
//        SweetAlertDialog finalPDialog = pDialog;
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//
//        Call<Response_login> sendbio = null;
//        if (jenis.equals("new")){
//            sendbio = api.simpan_optik(nama,
//                    alamat,
//                    phone,
//                    lat,
//                    lng,
//                    status,
//                    status_bpjs,
//                    asu,
//                    jam,
//                    informasi,
//                    foto);
//        }else {
//            sendbio = api.edit_optik(
//                    id,
//                    nama,
//                    alamat,
//                    phone,
//                    lat,
//                    lng,
//                    status,
//                    status_bpjs,
//                    asu,
//                    jam,
//                    informasi,
//                    foto);
//        }
//
//
//        sendbio.enqueue(new Callback<Response_login>() {
//            @Override
//            public void onResponse(Call<Response_login> call, Response<Response_login> response) {
//
//                try {
//                    String kode = response.body().getKode();
//                    String role = String.valueOf(response.body().getRole());
//                    Log.i("role_kode", "onResponse: " + role);
//
//                    //role = 1 = pemilik
//                    //role = 2 = usert
//                    countryView.status(kode,"");
//                    if (kode.equals("1")) {
//                        finalPDialog.dismissWithAnimation();
////                    login_new(email,password,finalPDialog);
//                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
//
//                    } else {
//                        finalPDialog.dismissWithAnimation();
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
//            public void onFailure(Call<Response_login> call, Throwable t) {
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



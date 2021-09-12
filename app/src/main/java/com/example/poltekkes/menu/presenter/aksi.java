package com.example.poltekkes.menu.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.example.poltekkes.menu.menu.menu_login;
import com.example.poltekkes.menu.menu.menu_utama;
import com.example.poltekkes.menu.model.login.Response_login;
import com.example.poltekkes.menu.server.ApiRequest;
import com.example.poltekkes.menu.server.Retroserver_server_AUTH;
import com.example.poltekkes.menu.view.coba_view;
import com.github.squti.guru.Guru;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class aksi {

    private Context ctx;
    private coba_view countryView;
    private Retroserver_server_AUTH countryService;
    public aksi(coba_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }





    public void login(String username, String password, ProgressDialog pDialog) {
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logging In...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.login(username,password);

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {
                String kode = null;
                try {
                    //kode = response.body().getSuccess();
                    Log.i("isi_kode", "onResponse: "+kode);
                    //adasdas


                    if (response.body().isSuccess()==true) {
                        finalPDialog.dismiss();
                        String nim = String.valueOf(response.body().getData().getUser().getNim());
                        String nama = String.valueOf(response.body().getData().getUser().getNamaLengkap());

                            new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
                            Guru.putString("status_loign", "true");
                            Guru.putString("nama", nama);
                            Guru.putString("nim", nim);
                            Guru.putString("auth", response.body().getData().getToken());
                            Intent intent = new Intent((Activity) ctx, menu_utama.class);
                            intent.putExtra("Fragmentone", 3); //pass zero for Fragmentone.
                            ctx.startActivity(intent);
                            CustomIntent.customType((Activity) ctx,"fadein-to-fadeout");


                    } else {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    }
                }catch (Exception e){
                    Log.i("isi_kode", "onResponse: "+kode);
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                Log.e("cek_eror_login", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
//
//    public void register(String nik,String username,String password, SweetAlertDialog pDialog,String email,String alamat,String telpon,int role ) {
//        pDialog = new SweetAlertDialog((Activity) ctx, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
//        pDialog.setTitleText("Simpan Data");
//        pDialog.setContentText("Mohon tunggu sedang memproses...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//
//        SweetAlertDialog finalPDialog = pDialog;
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//        Call<Response_login> sendbio = api.register(nik,username,email,alamat,telpon,role,password,password);
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
//                    if (kode.equals("1")) {
//                        finalPDialog.dismissWithAnimation();
////                    login_new(email,password,finalPDialog);
//                        dialog_berhasil_register("Berhasil",response.body().getMessage());
//                    } else {
//                        finalPDialog.dismissWithAnimation();
//                        dialog_gagal("Gagal",response.body().getMessage());
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
//                Log.e("cek_eror_login", "onFailure: "+t);
//
//                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
//            }
//        });
//
//    }
//



    public void update_password(String password,String password_baru,ProgressDialog pDialog) {

        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Update Password...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.edit_pass(password,password_baru);

        ProgressDialog finalPDialog = pDialog;
        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

//                boolean kode = response.body().isSuccess();
//                Log.i("kode_update", "onResponse: " + kode);
//
//                if (kode) {
//                    finalPDialog.dismiss();
//                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
//
//                }else {
//                    finalPDialog.dismiss();
//                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
//
//                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
    public  void logout(ProgressDialog pDialog){
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logout...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.logout();

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                boolean kode = response.body().isSuccess();
                Log.i("kode", "onResponse: " + kode);
                try {
                    if (kode) {
                        finalPDialog.dismiss();
                        Guru.putString("status_loign", "false");
                        Guru.putString("auth", "");
                        String status_login = Guru.getString("status_loign", "false");
                        Intent intent = new Intent((Activity) ctx, menu_login.class);
                        intent.putExtra("Fragmentone", 3);
                        ctx.startActivity(intent);
                        CustomIntent.customType((Activity) ctx, "fadein-to-fadeout");

                    } else {
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                    }
                }catch (Exception E){
                    Log.i("cek_error_logout", "onResponse: "+E);

                }



            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }


//    public void get_data_user(){
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//        Call<Response_user> call = api.get_data_user();
//        call.enqueue(new Callback<Response_user>() {
//            @Override
//            public void onResponse(Call<Response_user> call, Response<Response_user> response) {
//
//                try {
//
//                    if (response.isSuccessful()) {
//                        Response_user data = response.body();
//                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.i("isi_data", "onResponse: "+data);
//                        if (data != null && data.getIsi() != null) {
//                            List<IsiItem_user> result = data.getIsi();
//                            countryView.data_user(result);
//                        }
//
//
//
//                    } else {
//                        // error case
//                        switch (response.code()) {
//                            case 404:
//
//                                Toast.makeText(ctx, "not found", Toast.LENGTH_SHORT).show();
//                                break;
//                            case 500:
//
//                                Toast.makeText(ctx,"server broken", Toast.LENGTH_SHORT).show();
//                                break;
//                            default:
//
//                                Toast.makeText(ctx, "Notif error", Toast.LENGTH_SHORT).show();
//                                break;
//                        }
//                    }
//
//                } catch (Exception e) {
//                    Log.e("onResponse", "There is an error" + e);
//                    //data();
//                    e.printStackTrace();
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<Response_user> call, Throwable t) {
//                t.printStackTrace();
//                //  sliderView_bener.setBackgroundResource(R.drawable.bg_no_item_city);
//                Log.i("ewkwkwkwkw", "onFailure: " + t);
//                if (t instanceof IOException) {
//
//                } else {
//
//                }
//
//            }
//        });
//
//    }
    void dialog_berhasil_register(String judul,String pesan) {
        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(judul);
        pDialog.setContentText(pesan);
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
//
                Intent intent = new Intent(ctx, menu_login.class);
                ctx.startActivity(intent);
                CustomIntent.customType(ctx, "fadein-to-fadeout");
            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
    void dialog_gagal(String judul,String pesan) {
        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(judul);
        pDialog.setContentText(pesan);
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
}



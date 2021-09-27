package com.example.poltekkes.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.poltekkes.model.action.Response_simpan_data;
import com.example.poltekkes.server.ApiRequest;
import com.example.poltekkes.server.Retroserver_server_AUTH;
import com.example.poltekkes.view.riset_view;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class riset_password {

    private Context ctx;
    private riset_view countryView;
    private Retroserver_server_AUTH countryService;
    public riset_password(riset_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }

    public void riset(String nim, String username) {
        ProgressDialog  pDialog = new ProgressDialog(ctx);
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logging In...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_simpan_data> sendbio = api.riset(username,nim);

        sendbio.enqueue(new Callback<Response_simpan_data>() {
            @Override
            public void onResponse(Call<Response_simpan_data> call, Response<Response_simpan_data> response) {
                try {
                    //kode = response.body().getSuccess();
                    //adasdas
                    Log.i("cek_error_login", "onResponse: "+response.body());
                    if (response.body().isSuccess()) {
                       countryView.riset("1",response.body().getMessage());
                    } else {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    }
                }catch (Throwable e){
                    Toast.makeText(ctx, ""+response.code(), Toast.LENGTH_SHORT).show();
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_simpan_data> call, Throwable t) {
                Log.e("cek_eror_login", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
}



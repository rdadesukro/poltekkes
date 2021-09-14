package com.example.poltekkes.server;



import com.example.poltekkes.model.action.Response_action;
import com.example.poltekkes.model.login.Response_login;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.model.slider.Response_slider;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiRequest {



    @Multipart
    @POST("edit_foto_profil")
    Call<Response_login> edit_foto(@Part MultipartBody.Part foto);

    @FormUrlEncoded
    @POST("register")
    Call<Response_login> register(
            @Field("nim") String nim,
            @Field("username") String username,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("password") String password,
            @Field("konfirmasi_password") String konfirmasi_password);

    @FormUrlEncoded
    @POST("register")
    Call<Response_action> simpan_pertanyaan(
            @Field("nama") String nama,
            @Field("berat") String berat,
            @Field("panjang") String panjang,
            @Field("jawaban") String jawaban,
            @Field("rentang_usia_bayi") String rentang_usia_bayi);


    @FormUrlEncoded
    @POST("riset_password")
    Call<Response_action> edit_password(
            @Field("kode") String kode,
            @Field("email") String email,
            @Field("password_baru") String password_baru);





    @FormUrlEncoded
    @POST("edit_pass")
    Call<Response_login> edit_pass(
            @Field("password") String password,
            @Field("password_baru") String password_baru);




    @GET("sliders")
    Call<Response_slider> get_slider();





    @FormUrlEncoded
    @POST("user/login")
    Call<Response_login> login(
            @Field("username") String username,
            @Field("password") String password);




    @GET("penilaian/{tgl_lahir}")
    Call<Response_pertanyaan> get_pertanyaan(
            @Path("tgl_lahir") String tgl_lahir);



    @FormUrlEncoded
    @POST("simpan_pertanyaan")
    Call<Response_action> simpan_curhatan(@Field("isi_pertanyaan") String isi_pertanyaan);

    @FormUrlEncoded
    @POST("cek_data")
    Call<Response_login> cek_data(
            @Field("email") String username,
              @Field("password") String password);



    @POST("user/logout")
    Call<Response_login> logout();



}



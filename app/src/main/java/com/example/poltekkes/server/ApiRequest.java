package com.example.poltekkes.server;



import com.example.poltekkes.model.action.Response_action;
import com.example.poltekkes.model.action.Response_simpan_data;
import com.example.poltekkes.model.detail_history.Response_detail_history;
import com.example.poltekkes.model.history.Response_history;
import com.example.poltekkes.model.login.Response_login;
import com.example.poltekkes.model.materi.Response_materi;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;
import com.example.poltekkes.model.rekomendasi.Response_rekomendasi;
import com.example.poltekkes.model.slider.Response_slider;
import com.example.poltekkes.model.tindakan_perkembangan.Response_perkembangan;
import com.example.poltekkes.model.umur.Response_umur;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface ApiRequest {



    @Multipart
    @POST("edit_foto_profil")
    Call<Response_login> edit_foto(@Part MultipartBody.Part foto);

    @FormUrlEncoded
    @POST("user/register")
    Call<Response_login> register(
            @Field("nim") String nim,
            @Field("username") String username,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("password") String password,
            @Field("konfirmasi_password") String konfirmasi_password);

    @FormUrlEncoded
    @POST("user/jawaban/kirim")
    Call<Response_simpan_data> simpan_semua_data(
            @Field("nama_balita") String nama,
            @Field("tanggal_lahir") String tgl_lahir,
            @Field("nama_ibu") String nama_ibu,
            @Field("alamat") String alamat,
            @Field("usia_dalam_bulan") String usia_dalam_bulan,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("berat") String berat_badan,
            @Field("panjang") String panjang_badan,
            @Field("kode_pertumbuhan") String pertumbuhan_kode,
            @Field("kode_rekomendasi") String rekomendasi_kode,
            @Field("kode_tindakan_perkembangan") String kode_tindakan_perkembangan,
            @Field("jawaban_array") String jawaban_array);


    @FormUrlEncoded
    @POST("riset_password")
    Call<Response_action> edit_password(
            @Field("kode") String kode,
            @Field("email") String email,
            @Field("password_baru") String password_baru);





    @FormUrlEncoded
    @POST("user/password/ubah")
    Call<Response_login> edit_pass(
            @Field("pass_lama") String password,
            @Field("pass_baru") String password_baru);




    @GET("sliders")
    Call<Response_slider> get_slider();





    @FormUrlEncoded
    @POST("user/login")
    Call<Response_login> login(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("user/password/reset")
    Call<Response_simpan_data> riset(
            @Field("username") String username,
            @Field("nim") String nim);



//
//    @GET("penilaian/{tgl_lahir}")
//    Call<Response_pertanyaan> get_pertanyaan(
//            @Path("tgl_lahir") String tgl_lahir);

    @GET("balita/perkembangan/{tgl_lahir}")
    Call<Response_pertanyaan> get_pertanyaan(@Path("tgl_lahir") String tgl_lahir);

    @GET("user/jawaban/histori")
    Call<Response_history> get_history_user();


    @GET("user/jawaban/histori/admin")
    Call<Response_history> get_history_admin();


    @GET("user/jawaban/histori/detail/{id}")
    Call<Response_detail_history> get_history_detail(@Path("id") String id);

    @GET("balita/umur/{tgl_lahir}")
    Call<Response_umur> get_umur(
            @Path("tgl_lahir") String tgl_lahir);

    @FormUrlEncoded
    @POST("balita/perkembangan/hasil")
    Call<Response_perkembangan> get_perkembangan(
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jawaban") String jawaban);


    @FormUrlEncoded
    @POST("balita/pertumbuhan")
    Call<Response_rekomendasi> get_rekomendasi(
            @Field("berat_badan") String berat_badan,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("usia_dalam_bulan") String usia_dalam_bulan);


    @POST("image/data_materi.json")
    Call<Response_materi> get_materi();



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



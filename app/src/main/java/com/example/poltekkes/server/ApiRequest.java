package com.example.poltekkes.server;



import com.example.poltekkes.model.action.Response_action;
import com.example.poltekkes.model.login.Response_login;
import com.example.poltekkes.model.pertanyaan.Response_pertanyaan;

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


    @FormUrlEncoded
    @POST("send_email")
    Call<Response_action> send_email(
            @Field("email") String email);


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

    @Multipart
    @POST("add_optik")
    Call<Response_login> simpan_optik(
            @Part("nama_optik") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("phone") RequestBody phone,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("status") RequestBody status,
            @Part("status_bpjs") RequestBody status_bpjs,
            @Part("asuransi") RequestBody asuransi,
            @Part("jam_oprasional") RequestBody jam_oprasional,
            @Part("informasi") RequestBody informasi,
            @Part MultipartBody.Part foto);



    @Multipart
    @POST("add_jenis")
    Call<Response_login> add_jenis(
            @Part("nama") RequestBody nama,
            @Part("informasi") RequestBody informasi,
            @Part("harga") RequestBody harga,
            @Part("lapangan_id") RequestBody lapangan_id,
            @Part("status") RequestBody status,
            @Part MultipartBody.Part foto);


    @FormUrlEncoded
    @POST("riset_password")
    Call<Response_action> edit_password(
            @Field("kode") String kode,
            @Field("email") String email,
            @Field("password_baru") String password_baru);


    @Multipart
    @POST("edit_optik")
    Call<Response_login> edit_optik(
            @Part("id") RequestBody id,
            @Part("nama_optik") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("phone") RequestBody phone,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("status") RequestBody status,
            @Part("status_bpjs") RequestBody status_bpjs,
            @Part("asuransi") RequestBody asuransi,
            @Part("jam_oprasional") RequestBody jam_oprasional,
            @Part("informasi") RequestBody informasi,
            @Part MultipartBody.Part foto);



    @FormUrlEncoded
    @POST("edit_pass")
    Call<Response_login> edit_pass(
            @Field("password") String password,
            @Field("password_baru") String password_baru);


    @FormUrlEncoded
    @POST("add_raiting")
    Call<Response_login> add_raiting(
            @Field("jenis_id") String jenis_id,
            @Field("raiting") float raiting,
            @Field("lapangan_id") String lapangan_id);

    @Multipart
    @POST("add_foto")
    Call<Response_login> add_foto(
            @Part("optik_id") RequestBody optik_id,
            @Part("nama") RequestBody nama,
            @Part MultipartBody.Part foto);

    @Multipart
    @POST("edit_foto")
    Call<Response_login> edit_foto(
            @Part("id") RequestBody id,
            @Part("optik_id") RequestBody optik_id,
            @Part("nama") RequestBody nama,
            @Part MultipartBody.Part foto);



    @FormUrlEncoded
    @POST("add_komentar")
    Call<Response_login> add_komentar(
            @Field("jenis_id") String jenis_id,
            @Field("komentar") String komentar);



    @FormUrlEncoded
    @POST("login")
    Call<Response_login> login(
            @Field("username") String username,
            @Field("password") String password);




    @GET("penilaian/{tgl_lahir}")
    Call<Response_pertanyaan> get_pertanyaan(
            @Path("tgl_lahir") String tgl_lahir);


//    @POST("get_data_optik")
//    Call<Response_optik> get_optik();
//
//
//    @FormUrlEncoded
//    @POST("get_detail_optik")
//    Call<Response_optik> get_detail_optik( @Field("id") String id);
//
//
//    @POST("get_foto_slider")
//    Call<Response_slider> get_foto();






//
//
//    @GET("get_foto_slider")
//    Call<Response_slider> get_slider(@Query("id") String id);
//





    @FormUrlEncoded
    @POST("simpan_pertanyaan")
    Call<Response_action> simpan_curhatan(@Field("isi_pertanyaan") String isi_pertanyaan);

    @FormUrlEncoded
    @POST("cek_data")
    Call<Response_login> cek_data(
            @Field("email") String username,
              @Field("password") String password);


//
//    @POST("get_data_user")
//    Call<Response_user> get_data_user();
//

    @POST("logout")
    Call<Response_login> logout();

    @FormUrlEncoded
    @POST("hapus_token")
    Call<Response_login> hapus_token(
            @Field("token") String token);

    @FormUrlEncoded
    @POST("hapus_optik")
    Call<Response_action> hapus_optik(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("hapus_foto")
    Call<Response_action> hapus_foto(
            @Field("id") String id);



    @FormUrlEncoded
    @POST("edit_no_hp")
    Call<Response_login> edit_no_hp(@Field("phone") String no_hp);





}



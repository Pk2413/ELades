package com.ELayang.Desa.API;

import com.ELayang.Desa.DataModel.Lupa_Password.ResponPassword1;
import com.ELayang.Desa.DataModel.Lupa_Password.ResponPassword2;
import com.ELayang.Desa.DataModel.ResponLogin;
import com.ELayang.Desa.DataModel.Register.ResponOTP;
import com.ELayang.Desa.DataModel.Register.ResponRegister1;
import com.ELayang.Desa.DataModel.Register.ResponRegister2;
import com.ELayang.Desa.DataModel.Register.ResponRegister3;
import com.ELayang.Desa.DataModel.ResponSkck;
import com.ELayang.Desa.DataModel.ResponSurat;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    //buat ngambil data dari API/webservice retrieve.php
    @GET("retrieve.php")
    Call<ResponLogin> ardRetrieveData();

    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );


    @GET("surat.php")
    Call<ResponSurat> surat();


@FormUrlEncoded
@POST("surat/skck.php")
Call<ResponSkck> skck(
        @Field("nama") String nama,
        @Field("nik") String nik,
        @Field("tempat_tanggal_lahir") String tempat_tanggal_lahir,
        @Field("kebangsaan") String kebangsaan,
        @Field("agama") String agama,
        @Field("status_perkawinan") String status,
        @Field("pekerjaan") String pekerjaan,
        @Field("tempat_tinggal") String tinggal,
        @Field("username") String username,
        @Field("jenis_kelamin") String jenis_kelamin

);

    @FormUrlEncoded
    @POST("register/register1.php")
    Call<ResponRegister1> register1(
            @Field("username") String username,
            @Field("email") String email,
            @Field("nama") String nama

    );

    @FormUrlEncoded
    @POST("register/register2.php")
    Call<ResponRegister2> register2(
            @Field("username") String username,
            @Field("kode_otp") String kode_otp

    );

    @FormUrlEncoded
    @POST("register/register3.php")
    Call<ResponRegister3> register3(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register/kode_otp.php")
    Call<ResponOTP> kirim_otp(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("lupa_password/password1.php")
    Call<ResponPassword1> lupa_password1(
            @Field("username") String username
    );
    @FormUrlEncoded
    @POST("lupa_password/password2.php")
    Call<ResponPassword2> lupa_password2(
            @Field("kode_otp") String kode_otp,
            @Field("password") String password,
            @Field("username") String username
    );



}

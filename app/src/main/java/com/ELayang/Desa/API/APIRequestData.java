package com.ELayang.Desa.API;

import com.ELayang.Desa.DataModel.ModelResponse;
import com.ELayang.Desa.DataModel.ResponSurat;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIRequestData {
    //buat ngambil data dari API/webservice retrieve.php
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieveData();

    @FormUrlEncoded
    @POST("Login.php")
    Call<ModelResponse> login(
            @Field("username") String email,
            @Field("password") String password
    );


    @GET("surat.php")
    Call<ResponSurat> surat();





}

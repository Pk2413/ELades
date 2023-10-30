package com.ELayang.Desa.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.0.105/coding/ELaDes%20WEB/DatabaseMobile/";
    // http:// ip internetmu // letak file buat Mobile

    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro == null){

            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
package com.ELayang.Desa.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.137.253/coding/ELaDes%20WEB/DatabaseMobile/";
//    private static final String baseURL = "https://4667-114-9-18-66.ngrok-free.app/DatabaseMobile/";
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
package com.ELayang.Desa.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static String server = "192.168.1.10";
    private static final String baseURL = "http://" + server + "/coding/ELaDes%20WEB/DatabaseMobile/";

    private static Retrofit retro;

    public static Retrofit konekRetrofit() {
        if (retro == null) {

            // Tambahkan logging interceptor
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            // Inisialisasi Retrofit dengan interceptor
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retro;
    }
}

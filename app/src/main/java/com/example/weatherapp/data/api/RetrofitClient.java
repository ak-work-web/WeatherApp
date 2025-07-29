package com.example.weatherapp.data.api;

import static com.example.weatherapp.data.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static  Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

        //Androidでのモデル。Mモデル　Vビュー表示する。　V Mビューモデル。コントローラー的な役割
    }
}

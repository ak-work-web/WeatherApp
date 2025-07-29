package com.example.weatherapp.data.api;

//このimportは手入力した。
import com.example.weatherapp.data.model.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // 天気情報取得
    @GET("/data/2.5/weather")
    Call<WeatherResult> getWeatherDate(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}

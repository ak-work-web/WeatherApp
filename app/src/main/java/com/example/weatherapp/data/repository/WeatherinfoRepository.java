package com.example.weatherapp.data.repository;



import com.example.weatherapp.data.Constants;
import com.example.weatherapp.data.api.ApiService;
import com.example.weatherapp.data.api.RetrofitClient;
import com.example.weatherapp.data.model.WeatherResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherinfoRepository {
    private ApiService apiservice;

    public interface WeatherInfoResultCallback{
        void onSuccess(WeatherResult weatherResult);
        void onError(String error);
    }

    public WeatherinfoRepository(){
        apiservice = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public void getWeatherInfo(String cityName, WeatherInfoResultCallback callback){
        Call<WeatherResult> call =
                apiservice.getWeatherDate(cityName, Constants.API_KEY, Constants.UNITS_METRIC);

        //非同期でRetrofitリクエストを実行
        call.enqueue(new Callback<WeatherResult>(){

            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                //成功時にはデータを渡す
                if (response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onError(("response not success."));
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                //失敗時はエラーメッセージを残す
                callback.onError("APIリクエストが失敗しました。" + t.getMessage());
            }
        });

    }
}

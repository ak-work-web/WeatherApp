package com.example.weatherapp.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.data.WeatherInfoData;
import com.example.weatherapp.data.model.Weather;
import com.example.weatherapp.data.model.WeatherResult;
import com.example.weatherapp.data.repository.WeatherinfoRepository;

public class MainViewModel extends ViewModel{
 private static final String TAG = MainViewModel.class.getSimpleName();

//天気情報リポジトリ
private final WeatherinfoRepository weatherInfoRepository;
private final MutableLiveData<WeatherInfoData> weatherInfoData = new MutableLiveData<>();

public MainViewModel(){
    weatherInfoRepository = new WeatherinfoRepository();
}

public LiveData<WeatherInfoData> getWeatherInfoData() {
    return weatherInfoData;}
public  void fetchWeatherData(String cityNmae){
    Log.d(TAG, "fetchWeatherData:" + cityNmae);
if(weatherInfoRepository == null){
    Log.d(TAG, "WeaterInfoRepository is null.");
    return;
}

weatherInfoRepository.getWeatherInfo(cityNmae, new WeatherinfoRepository.WeatherInfoResultCallback() {
    @Override
    public void onSuccess(WeatherResult weatherResult) {
        //成功した場合
        Log.d(TAG, "onSuccess!");
        //WeatherResultをWeatherInfoDataに変換
        WeatherInfoData infoData = convertToWeatherInfoData(weatherResult);
        MainViewModel.this.weatherInfoData.setValue(infoData);
    }


    @Override
    public void onError(String error) {
        //エラーの場合
        Log.e(TAG, "onError:" + error);
    }
});
}
//天気情報をWeatherInfoDataに変換するメソッド
    private WeatherInfoData convertToWeatherInfoData(WeatherResult result){
     String cityName = result.getName();
     String weatherDescription = "";
     String iconResName = "";
     if(!result.getWeather().isEmpty()){
         Weather weather =  result.getWeather().get(0);
         weatherDescription = weather.getDescription();
         String iconCode = weather.getIcon();
         iconResName = "ic_" + iconCode;
     }
     float temperature = result.getMain().getTemp();
     float humidity = result.getMain().getHumidity();
     float windSpeed = result.getWind().getSpeed();

     return new WeatherInfoData (
            temperature,
                    humidity,
                    windSpeed,
                    weatherDescription,
                    iconResName,
                    cityName
     );
    }
}


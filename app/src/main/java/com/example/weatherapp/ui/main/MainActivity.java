package com.example.weatherapp.ui.main;

import static com.example.weatherapp.data.Constants.API_KEY;
import static com.example.weatherapp.data.Constants.UNITS_METRIC;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.data.api.ApiService;
import com.example.weatherapp.data.api.RetrofitClient;
import com.example.weatherapp.data.model.WeatherResult;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView cityNmaeText;
    private TextView temperatureText;
    private TextView humidityText;
    private TextView descriptionText;
    private TextView windText;
    private ImageView weahterIcon;
    private EditText cityNameInput;
    private MainViewModel viewModel;

//    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityNmaeText = findViewById(R.id.cityNameText);
        temperatureText = findViewById(R.id.temperatureText);
        humidityText = findViewById(R.id.humidityText);
        windText = findViewById(R.id.windText);
        descriptionText = findViewById(R.id.descriptionText);
        cityNameInput = findViewById(R.id.cityNameInput);
        weahterIcon = findViewById(R.id.weatherIcon);
        Button refresButton = findViewById(R.id.fetchWeatherButton);

        refresButton.setOnClickListener(v -> {
            String cityName = cityNameInput.getText().toString();
            if (!cityName.isEmpty()) {
                viewModel.fetchWeatherData(cityName);
            } else {
                cityNameInput.setError("Please enter a city name");

            }
        });

        //ViewModelの初期化
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getWeatherInfoData().observe(this, weatherInfoData -> {
            //天気情報の更新
            cityNmaeText.setText(weatherInfoData.getCityName());
            temperatureText.setText(String.format(Locale.US, "%.0f°", weatherInfoData.getTemperature()));
            humidityText.setText(String.format(Locale.US, "%.0f%%", weatherInfoData.getHumidity()));
            windText.setText(String.format(Locale.US, "%.0f km/h", weatherInfoData.getWindSpeed()));
            descriptionText.setText(weatherInfoData.getWeatherDescription());
            //画像名と一致する画像IDをローカルから取得
            int resId = getResources().getIdentifier(weatherInfoData.getIconResName(), "drawable", getPackageName());
        });

        viewModel.fetchWeatherData("Mumbai");

//        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
//
//        Call<WeatherResult> call = apiService.getWeatherDate("Tokyo", API_KEY, UNITS_METRIC);
//
//        //非同期でRetrofitリクエストを実行
//        call.enqueue(new Callback<WeatherResult>(){
//        @Override
//            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
//                WeatherResult result = response.body();
//                String main = result.getWeather().get(0).getMain();
//                Log.d("TEST", "response: " + main);
//            }
//
//            @Override
//            public void onFailure(Call<WeatherResult> call, Throwable t){
//                Log.d("TEST", "取得に失敗！" + t.getMessage());
//            }
//        });
    }
}
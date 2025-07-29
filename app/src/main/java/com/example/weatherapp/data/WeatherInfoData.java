package com.example.weatherapp.data;

public class WeatherInfoData {
    private float temperature;
    private  float  humidity;

    private  float windSpeed;
    private String  weatherDescription;
    private String iconResName;
    private  String cityName;

    public WeatherInfoData(float temperature, float humidity, float windSpeed, String weatherDescription, String iconResName, String cityName){
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherDescription = weatherDescription;
        this.iconResName = iconResName;
        this.cityName = cityName;
    }

    public float getTemperature(){
        return temperature;
    }

    public void setTemperature(float temperature){this.temperature = temperature;}
    public float getHumidity(){ return humidity;}



    public float getWindSpeed() {
        return windSpeed;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getIconResName() {
        return iconResName;
    }

    public void setIconResName(String iconResName) {
        this.iconResName = iconResName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

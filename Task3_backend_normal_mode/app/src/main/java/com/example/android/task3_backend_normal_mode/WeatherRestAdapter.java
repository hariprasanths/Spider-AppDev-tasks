package com.example.android.task3_backend_normal_mode;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Hari on 05-07-2017.
 */

public class WeatherRestAdapter {

    private RestAdapter restAdapter;
    private WeatherApiInterface weatherApi;
    private static final String WEATHER_URL = "http://api.openweathermap.org";
    private static final String WEATHER_API_APPID = "467516b940fdba088efbdb5545ae2954";

    public interface WeatherApiInterface {

        @GET("/data/2.5/weather")
        void getWeatherFromApi (
                @Query("q") String cityName,
                @Query("APPID") String appId,
                Callback<WeatherData> callback);
    }

    public WeatherRestAdapter()
    {
        restAdapter = new RestAdapter.Builder().setEndpoint(WEATHER_URL).build();
        weatherApi = restAdapter.create(WeatherApiInterface.class);
    }

        public void getWeatherData(String cityName, Callback<WeatherData> callback) {
            weatherApi.getWeatherFromApi(cityName,WEATHER_API_APPID,callback);

    }
}


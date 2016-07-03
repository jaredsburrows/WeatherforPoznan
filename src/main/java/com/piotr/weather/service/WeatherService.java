package com.piotr.weather.service;

/**
 * @author piotr on 17.09.15.
 */

import com.piotr.weather.model.Forecast;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {

    // http://api.openweathermap.org/data/2.5/forecast/city?id=3088171&mode=json&units=imperial&type=hour&lang=en&appid=cf28f00904e59e7ba0fc080879080861
    //
    // http://api.openweathermap.org/data/2.5/forecast/city
    // ?id=5400075
    // &mode=json
    // &units=imperial
    // &type=hour
    // &lang=en
    // &appid=cf28f00904e59e7ba0fc080879080861
    @GET("/data/2.5/forecast/city")
    void getForecast(@Query("id") int id, @Query("mode") String mode, @Query("units") String units,
                     @Query("type") String type, @Query("lang") String lang, @Query("appid") String apiId,
                     Callback<Forecast> cb);
}

package com.example.ekz;

import android.os.Build;
import android.os.Looper;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.Http2Reader;

public class ApiClient {
    private final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
    public void GetWeatherForecast(final MainActivity main) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.weather.yandex.ru/v2/forecast?lat=58.0050&lon=56.1456")
                        .header("X-Yandex-Weather-Key", "demo_yandex_weather_api_key_ca6d09349ba0")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Запрос к серверу не был успешен: " +
                                response.code() + " " + response.message());
                    }

                    String responseData = response.body().string();
                    handler.post(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            try {
                                JSONObject forecast = new JSONObject(responseData);
                                System.out.println(forecast);
                                if (forecast != null) {
                                    try {
                                        JSONArray days = forecast.getJSONArray("forecasts");
                                        DayWeather.DayWeatherList.clear();
                                        for (int i = 0; i < days.length(); i++) {
                                            forecast = days.getJSONObject(i);
                                            String date = forecast.getString("date");
                                            JSONObject parts = forecast.getJSONObject("parts");
                                            JSONObject day = parts.getJSONObject("day");
                                            String temp = day.getString("temp_avg");
                                            String weather = day.getString("condition");
                                            String dayOfWeek = (LocalDate.parse(date).getDayOfWeek()).toString();

                                            if(i == 0){
                                                MainActivity.Temperature.setText(temp + "°");
                                                MainActivity.Weather.setText(weather);
                                            }

                                            DayWeather.DayWeatherList.add(new DayWeather(dayOfWeek, temp, weather));

                                            System.out.println("Дата: " + dayOfWeek);
                                            System.out.println("Средняя температура: " + temp);
                                            System.out.println("Условия: " + weather);
                                        }
                                        main.getForecast();
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    System.out.println("Не удалось получить данные");
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Ошибка подключения: " + e.toString());
                        }
                    });
                }
            }
        }).start();
    }
}

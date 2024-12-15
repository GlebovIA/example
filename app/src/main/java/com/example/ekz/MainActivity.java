package com.example.ekz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static LinearLayout List;
    public static TextView Temperature;
    public static TextView Weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List = findViewById(R.id.list);
        Temperature = findViewById(R.id.temperature);
        Weather = findViewById(R.id.weather);
        ApiClient client = new ApiClient();
        client.GetWeatherForecast(this);
    }

    public void getForecast(){
        if(DayWeather.DayWeatherList.isEmpty()){
            System.out.println("Нет данных для отображения");
            return;
        }
        for(DayWeather weather : DayWeather.DayWeatherList){
            DayElement element = new DayElement(this, null);
            element.setParametrs(weather.dayOfWeek, weather.temperature, weather.weather);
            List.addView(element);
        }
    }

}
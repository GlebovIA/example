package com.example.ekz;

import java.util.ArrayList;
import java.util.List;

public class DayWeather {
    public static List<DayWeather> DayWeatherList = new ArrayList<>();
    public String dayOfWeek;
    public String temperature;
    public String weather;
    public DayWeather(String day, String temp, String weath){
        this.dayOfWeek = day;
        this.temperature = temp;
        this.weather = weath;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public String getWeather() {
        return weather;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}

package com.example.ekz;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DayElement extends androidx.constraintlayout.widget.ConstraintLayout{
    public TextView DayOfWeek;
    public TextView Temperature;
    public TextView Weather;

    public DayElement(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.day_element, this, true);
        DayOfWeek = findViewById(R.id.dayOfWeek);
        Temperature = findViewById(R.id.temperature);
        Weather = findViewById(R.id.weather);
    }

    public void setParametrs(String day, String temp, String weath) {
        DayOfWeek.setText(day);
        Temperature.setText(temp);
        Weather.setText(weath);
    }
}

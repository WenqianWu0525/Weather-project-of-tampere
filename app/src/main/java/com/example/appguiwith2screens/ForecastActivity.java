package com.example.appguiwith2screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        //WE're opening the screen here
       Intent intent = getIntent();
        String cityName = intent.getStringExtra("CITY_NAME");
        String somethingNotThere = intent.getStringExtra("NOT_THERE");
        int howManyDays = intent.getIntExtra("How_MANY_DAYS", 6);

        TextView forecastheaderTextView = null;
        if (cityName != null) {
         forecastheaderTextView = findViewById(R.id.forecastheaderTextView);
        forecastheaderTextView.setText(cityName);
         } else {
           forecastheaderTextView.setText(R.string.LOCATION_NOT_KNOWN);
        }

    }


    public void openMainActivity(View view) {
        Intent openMain = new Intent(this, MainActivity.class );
       startActivity( openMain );
    }
}
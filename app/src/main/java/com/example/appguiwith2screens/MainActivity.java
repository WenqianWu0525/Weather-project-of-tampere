package com.example.appguiwith2screens;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private String description = "Click to refresh";
    private double temperature = 0;
    private double windSpeed = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create a new web request queue
        queue = Volley.newRequestQueue(this);
        //now we are ready to send the request with the queue
        //write the values on the UI


    } //Activity lifecycle methods
    protected void onStart() {
        super.onStart(); //Activity is about to become visible.
        Log.d("MY_APP", "MainActivity: onStartCalled");
     }

    protected void onDestroy() {

        super.onDestroy();// The activity is about to be destroyed.
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("WEATHER_DESCRIPTION", description);
        outState.putDouble("TEMPERATURE", temperature);
        outState.putDouble("WIND",windSpeed);
    }
    // this is an optional bundle reading method if you don't want to do it in onCreate
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
            //there is some app data saved
            description = savedInstanceState.getString("WEATHER_DESCRIPTION");
            if (description == null){
                description = "Click to refresh";
            }
            temperature = savedInstanceState.getDouble("TEMPERATURE",0);
            windSpeed = savedInstanceState.getDouble("WIND", 0);

        TextView descriptionTextView = findViewById(R.id.headerTextView5);
        descriptionTextView.setText(description);
        TextView temperatureTextView = findViewById(R.id.headerTextView2);
        temperatureTextView.setText("" + temperature + "C");
        TextView windTextView = findViewById(R.id.headerTextView4);
        windTextView.setText("" + windSpeed +"m/s");


    }

    public void openForecastActivity(View view) {
        // Switch to the other screen and send a massage to that screen
        Intent openForecast = new Intent(this, ForecastActivity.class);
        openForecast.putExtra("CITY_NAME","Tampere");
        openForecast.putExtra("HOW_MANY_DAYS", 5);
        startActivity( openForecast );
    }
    public void refreshData(View view) {
        //make the request here and put it in the queue to get the response
        String url ="https://api.openweathermap.org/data/2.5/weather?q=tampere&appid=6c433438776b5be4ac86001dc88de74d";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                response -> {
                    Log.d("WEATHER_APP", response);
                    parseJsonAndUpdateUI( response );
                }, error -> {
                    Log.d("WEATHER_APP", error.toString());

                });
        //Add the request to the queue to actually send it
        queue.add(stringRequest);
        
        }

    private void parseJsonAndUpdateUI(String response) {
        try {
            JSONObject weatherResponse = new JSONObject(response);

            description = weatherResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            temperature = weatherResponse.getJSONObject("main").getDouble("temp");
            windSpeed = weatherResponse.getJSONObject("wind").getDouble("speed");

            //write the values on the UI
            TextView descriptionTextView = findViewById(R.id.headerTextView5);
            descriptionTextView.setText(description);
            TextView temperatureTextView = findViewById(R.id.headerTextView2);
            temperatureTextView.setText("" + temperature + "C");
            TextView windTextView = findViewById(R.id.headerTextView4);
            windTextView.setText("" + windSpeed +"m/s");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void openWebPage(View view) {
        //open https://www.accuweather.com/en/fi/tampere/134771/march-weather/134771
        String urlString = "https://www.accuweather.com/en/fi/tampere/134771/march-weather/134771";
        Uri uri = Uri.parse(urlString);
        //Create the implicit intent
        //Implicit intent == the system will choose the activity to be used
        //Explicit intent == the developer chooses the activity, e.g. MySecondActivity.class
        Intent openWebPage = new Intent(Intent.ACTION_VIEW, uri);

        //so we do this instead...
        try {
            startActivity( openWebPage );
        }
        catch( ActivityNotFoundException e ){
            //there is no viewer for web pages (http)

        }
        //if( openWebPage.resolveActivity(getPackageManager()) !=null) {

        //}

    }

    public void setAlarm(View view) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Timout")
                .putExtra(AlarmClock.EXTRA_LENGTH, 20)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        try {
            startActivity(intent);
        }
        catch( ActivityNotFoundException e){

        }


    }
    public void openaWebPage(View view) {
        //open https://www.wikihow.com/Predict-the-Weather-Without-a-Forecast
        String urlString = "https://www.wikihow.com/Predict-the-Weather-Without-a-Forecast";
        Uri uri = Uri.parse(urlString);
        //Create the implicit intent
        //Implicit intent == the system will choose the activity to be used
        //Explicit intent == the developer chooses the activity, e.g. MySecondActivity.class
        Intent openaWebPage = new Intent(Intent.ACTION_VIEW, uri);

        //so we do this instead...
        try {
            startActivity( openaWebPage );
        }
        catch( ActivityNotFoundException e ){
            //there is no viewer for web pages (http)

        }
        //if( openWebPage.resolveActivity(getPackageManager()) !=null) {

        //}

    }

    public void opentheWebPage(View view) {
        //open www.tuni.fi
        String urlString = "https://www.google.com/maps";
        Uri uri = Uri.parse(urlString);
        //Create the implicit intent
        //Implicit intent == the system will choose the activity to be used
        //Explicit intent == the developer chooses the activity, e.g. MySecondActivity.class
        Intent opentheWebPage = new Intent(Intent.ACTION_VIEW, uri);

        //so we do this instead...
        try {
            startActivity( opentheWebPage );
        }
        catch( ActivityNotFoundException e ){
            //there is no viewer for web pages (http)

        }
        //if( openWebPage.resolveActivity(getPackageManager()) !=null) {

        //}

    }
}

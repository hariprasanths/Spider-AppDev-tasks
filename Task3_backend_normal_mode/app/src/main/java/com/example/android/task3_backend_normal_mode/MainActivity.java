package com.example.android.task3_backend_normal_mode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;
    EditText cityNameInputBox;
    Button searchButton;
    String cityName = "";
    WeatherRestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityNameInputBox = (EditText) findViewById(R.id.city_input_box);
        searchButton = (Button) findViewById(R.id.button);
        resultTextView = (TextView) findViewById(R.id.result_textview);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = cityNameInputBox.getText().toString().trim();
                cityNameInputBox.getText().clear();
                if(restAdapter == null)
                    restAdapter = new WeatherRestAdapter();
                restAdapter.getWeatherData(cityName,callBack);

            }
        });




    }

    private Callback<WeatherData> callBack = new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                String city = "City: " + weatherData.getName() ;
                String temperature = String.format(Locale.getDefault(),"Temperature: %.2f F", (weatherData.getTemp() - 273.15) * 1.8 + 32.00);
                String countryCode = "Country Code: " + weatherData.getCountry();
                String pressure = "Pressure: " + weatherData.getPressure() + " Pa";
                String humidity = "Humidity: " + weatherData.getMain().getHumidity() + " pc";
                String result = city + "\n" + temperature + "\n" + countryCode + "\n" + pressure + "\n" + humidity ;
                resultTextView.setText(result);

            }

        @Override
            public void failure(RetrofitError error) {
            Toast.makeText(getApplicationContext(),"Error Loading info",Toast.LENGTH_SHORT).show();
            }
        };

}

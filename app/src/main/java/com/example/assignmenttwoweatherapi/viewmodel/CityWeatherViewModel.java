package com.example.assignmenttwoweatherapi.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignmenttwoweatherapi.model.CityWeatherModel;
import com.example.assignmenttwoweatherapi.utils.ApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CityWeatherViewModel extends ViewModel {
    CityWeatherModel weatherModel = new CityWeatherModel(); //Initialize weather model
    private final MutableLiveData<CityWeatherModel> weatherData = new MutableLiveData<CityWeatherModel>(); //Initialize mutable live data

    //Getter for live data
    public LiveData<CityWeatherModel> getWeatherData() {
        return weatherData; //Returns weather data
    }


    //Method for getting api
    public void Refresh(String filter){
        String loading = "Loading";
        errorLoadHelper(loading);
        //Variable for url string
        String urlString ="https://api.weatherapi.com/v1/current.json?key=9e1a229e57c546a7b61204128261503&q=" + filter;

        //Call api service
        ApiClient.get(urlString, new Callback(){

            //On response method
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null; //assert response body isn't null
                String responseData = response.body().string(); //Variable for response body
                JSONObject json = null; //Initialize json object variable

                try {
                    json = new JSONObject(responseData); //Store response body as JSON object
                    JSONObject locationJson = json.getJSONObject("location");  //Gets location section from JSON and store in variable
                    JSONObject currentJson = json.getJSONObject("current");    //Gets current section from JSON and store in variable
                    JSONObject conditionJson = currentJson.getJSONObject("condition"); //Gets condition section from JSON and store in variable

                    //Variables for current condition
                    String name = locationJson.getString("name"); //Gets name from locationJson and stores in variable
                    String country = locationJson.getString("country"); //Gets country from locationJson and stores in variable
                    String celsius = currentJson.getString("temp_c") + " °C/"; //Gets celsius from currentJson and stores it in string variable
                    String fahrenheit = currentJson.getString("temp_f") + " °F"; //Get fahrenheit from currentJson and stores it in string variable
                    String condition = "Condition: " + conditionJson.getString("text"); //Get condition from conditionJson and stores it in string variable

                    //Variables for feelsLike Card
                    String feelsLikeCelsius = currentJson.getString("feelslike_c") + " °C/"; //Gets feels like celsius from currentJson and stores it in string variable
                    String feelsLikeFahrenheit = currentJson.getString("feelslike_f") + " °F"; //Gets feels like fahrenheit from currentJson and stores it in string
                    String windCelsius = currentJson.getString("windchill_c") + " °C/"; //Gets wind chill celsius from currentJson and stores it in string
                    String windFahrenheit = currentJson.getString("windchill_f") + " °F"; //Gets wind chill fahrenheit from currentJson and stores it in string
                    String humidity = "Humidity " + currentJson.getString("humidity"); //Gets feels like humidity from currentJson and stores it in string

                    //Variables for wind card
                    String windSpeed = "Wind Speed: " + currentJson.getString("wind_kph") + "/Kph"; //Gets wind speed from currentJson and stores it in string
                    String windDirection = "Wind Direction: " + currentJson.getString("wind_dir"); //Gets wind direction from currentJson and stores it in string

                    //Sets variables in weather model for condition card, passing the variables above for condition card
                    weatherModel.setName(name);
                    weatherModel.setCountry(country);
                    weatherModel.setCelsius(celsius);
                    weatherModel.setFahrenheit(fahrenheit);
                    weatherModel.setCondition(condition);

                    //Sets variables in weather model for feels like card, passing the variables above for feels like card
                    weatherModel.setFeelsLikeCelsius(feelsLikeCelsius);
                    weatherModel.setFeelsLikeFahrenheit(feelsLikeFahrenheit);
                    weatherModel.setWindChillCelsius(windCelsius);
                    weatherModel.setWindChillFahrenheit(windFahrenheit);
                    weatherModel.setHumidity(humidity);

                    //Sets variables in weather model for wind card, passing the variables above for wind card
                    weatherModel.setWindSpeed(windSpeed);
                    weatherModel.setWindDirection(windDirection);

                    weatherData.postValue(weatherModel); //Posts weather model to weather data
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String error = "Error"; //Variable for error
                Log.e("tag", "Api failed: ", e); //Logs failure
                errorLoadHelper(error);

            }
        });
    }
    public void errorLoadHelper(String message){
        if(message.equals("Loading")){ //Check if message is loading and sets values accordingly
            weatherModel.setName(message);
            weatherModel.setCountry(" Please wait..");
        }else{
            weatherModel.setName("ERROR");
            weatherModel.setCountry(" API CALL FAILED PLEASE TRY AGAIN!");
        }
        //Switches all values to value of message to let user know if loading or error
        weatherModel.setCelsius(message + " ");
        weatherModel.setFahrenheit(message + " ");
        weatherModel.setCondition(message + " ");
        weatherModel.setFeelsLikeCelsius(message + " ");
        weatherModel.setFeelsLikeFahrenheit(message + " ");
        weatherModel.setWindChillCelsius(message + " ");
        weatherModel.setWindChillFahrenheit(message + " ");
        weatherModel.setHumidity(message + " ");
        weatherModel.setWindSpeed(message);
        weatherModel.setWindDirection(message);

        //Post weather model to weather data
        weatherData.postValue(weatherModel);
    }
}


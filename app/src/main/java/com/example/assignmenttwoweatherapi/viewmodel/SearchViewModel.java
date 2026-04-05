package com.example.assignmenttwoweatherapi.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignmenttwoweatherapi.R;
import com.example.assignmenttwoweatherapi.model.SearchModel;
import com.example.assignmenttwoweatherapi.utils.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchViewModel extends ViewModel {

    List<SearchModel> results; //Initialize variable for results as a list of searchModels

    private String preventDoubleSearch = ""; //Initialize variable to prevent a double search of the same value

    private final MutableLiveData<List<SearchModel>> searchData = new MutableLiveData<List<SearchModel>>(); //Initialize variable for live data as a list of searchModels

    //Getter for Mutable Live Data
    public LiveData<List<SearchModel>> getSearchData(){
        return searchData;
    }

    //Function for calling api
    public void Refresh(String filter){
        filter = filter.trim(); //Trim filter for easier validation
        if (filter.isEmpty()){ //Check if filter empty
            searchData.postValue(new ArrayList<>()); //Set searchData to empty array
            return; //Return
        }

        if(preventDoubleSearch.equals(filter)) return; //Return if double search to prevent dupe api call
        preventDoubleSearch = filter; //Set double to previous filter

        List<SearchModel> loading = new ArrayList<>(); //Initialize new array list for loading
        loading.add(new SearchModel("Loading...", "", "", R.drawable.elpsis)); //Adds loading message to array
        searchData.postValue(loading); //Post value to search data

        String urlString = "https://api.weatherapi.com/v1/search.json?key=9e1a229e57c546a7b61204128261503&q=" + filter; //Variable for holding api string

        //Calls api
        ApiClient.get(urlString, new Callback() {

            //On response method
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null; //asserts response isnt null
                String responseData = response.body().string(); //Store response in variable
                JSONArray json = null; //Initialize json array variable


                try {
                    results = new ArrayList<>(); //Set up new array list
                    json = new JSONArray(responseData); //Set json array as response data
                    if(json.length() == 0 ){ //If no cities are found
                        results.add(new SearchModel("No City Found", "", "", R.drawable.question)); //Adds no city found
                    }

                    //Loops through each object
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject obj = json.getJSONObject(i); //Gets json object in variable

                        String cityName = obj.getString("name"); //Store name value from object in variable
                        String cityRegion = obj.getString("region"); //Store region value from object in variable
                        String country = obj.getString("country"); //Store country value from object in variable

                        results.add(new SearchModel(cityName, cityRegion, country, R.drawable.globe)); //Add object to result
                        Log.d("tag", cityName + ", " + cityRegion + ", " + country);
                    }

                    searchData.postValue(results); //Send result to searchData to be used as live data

                } catch (JSONException e) {
                    throw new RuntimeException(e); //Throws exception
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("tag", "Api failed: ", e); //Logs failure
                results = new ArrayList<>(); //Set up new array list
                results.add(new SearchModel("ERROR: API CALL FAILED PLEASE TRY AGAIN!", "", "", R.drawable.question)); //Adds error message for user
                searchData.postValue(results); //Send result to searchData to be used as live data
                preventDoubleSearch = ""; //Reset prevent double search

            }
        });

    }

}

package com.example.assignmenttwoweatherapi.utils;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

//Class for setting up API calls
public class ApiClient {
    private static final OkHttpClient client = new OkHttpClient(); //Variable for initializing okhttp
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8"); //Set json media type

    //Getter for getting api request
    public static void get(String url, Callback callback){
        //Setup request for api
        Request request = new Request.Builder()
                .url(url)
                .build();

        //Send request and callback data
        client.newCall(request).enqueue(callback);
    }
}


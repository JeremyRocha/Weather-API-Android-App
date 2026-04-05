package com.example.assignmenttwoweatherapi.model;

import com.example.assignmenttwoweatherapi.R;

//Model for city search
public class SearchModel {

    private String name; //Variable for name
    private final String region; //Variable for region
    private final String country; //Variable for country
    private final int image; //Variable for image


    //Constructor to set the value of variable based on parameters passed
    public SearchModel(String name, String region, String country, int image){
        this.name = name;
        this.region = region;
        this.country = country;
        this.image = image;
    }

    //Getter and setter for variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }


    public String getCountry() {
        return country;
    }


    public int getImage() {
        return image;
    }


}

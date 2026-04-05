package com.example.assignmenttwoweatherapi.model;

public class CityWeatherModel {
    //Current condition Variable
    private String name;
    private String country;
    private String celsius;
    private String fahrenheit;
    private String condition;

    //Feel like card variables
    private String feelsLikeCelsius;
    private String feelsLikeFahrenheit;
    private String windChillCelsius;
    private String windChillFahrenheit;
    private String humidity;

    //Wind card variable
    private String windSpeed;
    private String windDirection;


    //Getters and Setter for above variable to set or get values
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public void setFeelsLikeCelsius(String feelsLikeCelsius) {
        this.feelsLikeCelsius = feelsLikeCelsius;
    }

    public String getFeelsLikeFahrenheit() {
        return feelsLikeFahrenheit;
    }

    public void setFeelsLikeFahrenheit(String feelsLikeFahrenheit) {
        this.feelsLikeFahrenheit = feelsLikeFahrenheit;
    }

    public String getWindChillCelsius() {
        return windChillCelsius;
    }

    public void setWindChillCelsius(String windChillCelsius) {
        this.windChillCelsius = windChillCelsius;
    }

    public String getWindChillFahrenheit() {
        return windChillFahrenheit;
    }

    public void setWindChillFahrenheit(String windChillFahrenheit) {
        this.windChillFahrenheit = windChillFahrenheit;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }
}

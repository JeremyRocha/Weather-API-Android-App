package com.example.assignmenttwoweatherapi.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmenttwoweatherapi.R;
import com.example.assignmenttwoweatherapi.databinding.FragmentCityWeatherBinding;
import com.example.assignmenttwoweatherapi.viewmodel.CityWeatherViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CityWeatherFragment#} factory method to
 * create an instance of this fragment.
 */
public class CityWeatherFragment extends Fragment {

    private String cityFilter;
    CityWeatherViewModel cityWeatherViewModel; //Variable for view model
    FragmentCityWeatherBinding binding; //Variable constructor


    //Constructor with filter param
    public CityWeatherFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        assert getArguments() != null; //Asser argument aren't null
        cityFilter = getArguments().getString("cityFilter"); //Gets string value from argument using key

        binding = FragmentCityWeatherBinding.inflate(inflater, container, false); //Inflates fragment
        cityWeatherViewModel = new ViewModelProvider(this).get(CityWeatherViewModel.class); //Hook up view model
        cityWeatherViewModel.Refresh(cityFilter); //Call refresh method in view model to get api data


        //Observe weather data in order to keep data on changes to view
        cityWeatherViewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherData -> {
            //Sets text of current conditions card with values from weather data
            binding.cityCountryName.setText(weatherData.getName() + ", " + weatherData.getCountry());
            binding.temperature.setText("Temperature: " + weatherData.getCelsius() + weatherData.getFahrenheit());
            binding.weatherCondition.setText(weatherData.getCondition());

            //Sets text of feels like card with values from weather data
            binding.windChill.setText("Wind chill: " + weatherData.getWindChillCelsius() + weatherData.getWindChillFahrenheit());
            binding.feelsLike.setText("Feels like: " + weatherData.getFeelsLikeCelsius() + weatherData.getFeelsLikeFahrenheit());
            binding.humidity.setText(weatherData.getHumidity());

            //Sets text of wind card with values from weather data
            binding.windSpeed.setText(weatherData.getWindSpeed());
            binding.windDirection.setText(weatherData.getWindDirection());
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fn = getActivity().getSupportFragmentManager(); //Get fragment manager
                FragmentTransaction ft = fn.beginTransaction(); //Begin fragment transaction
                ft.replace(R.id.fragment, new SearchFragment()); //Replaces element with main id with searchFragment
                ft.commit(); //Commits transaction
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    //Method for passing city name to be used for api
    public static CityWeatherFragment passArgument(String cityFilter){
        CityWeatherFragment fragment = new CityWeatherFragment(); //Creates new fragment
        Bundle bundle = new Bundle(); //Creates new bundle
        bundle.putString("cityFilter", cityFilter); //Put city name into bundle attaching it specified key
        fragment.setArguments(bundle); //Sets arguments for fragment using the bundle
        return fragment; //returns fragment
    }
}
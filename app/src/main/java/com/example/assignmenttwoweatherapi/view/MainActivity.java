package com.example.assignmenttwoweatherapi.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.assignmenttwoweatherapi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; //Initialize variable for binding


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //setup binding
        setContentView(binding.getRoot()); //Set view to binding route
        if(savedInstanceState == null) { //Check if there's a saved instance to prevent data from reloading or screen rotation
            FragmentManager fn = getSupportFragmentManager(); //Get fragment manager
            FragmentTransaction ft = fn.beginTransaction(); //Begin fragment transaction
            ft.replace(binding.fragment.getId(), new SearchFragment()); //Replaces element with main id with searchFragment
            ft.commit(); //Commits transaction
        }

    }
}
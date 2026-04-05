package com.example.assignmenttwoweatherapi.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.assignmenttwoweatherapi.R;
import com.example.assignmenttwoweatherapi.databinding.FragmentSearchBinding;
import com.example.assignmenttwoweatherapi.model.SearchModel;
import com.example.assignmenttwoweatherapi.utils.ItemClickListener;
import com.example.assignmenttwoweatherapi.viewmodel.SearchAdapter;
import com.example.assignmenttwoweatherapi.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#} factory method to
 * create an instance of this fragment.
 * Fragment for search and recycle view
 */
public class SearchFragment extends Fragment implements ItemClickListener {

    Handler handler = new Handler(Looper.getMainLooper()); //Setup handler for query event delay
    Runnable runnable; //Setup runnable for query event delay
    SearchViewModel viewModel; //Initialize view model variable
    FragmentSearchBinding binding; //Initialize fragment binding variable
    List<SearchModel> searchList; //Initialize list for search result
    SearchAdapter searchAdapter; //Initialize search adapter variable for recycler


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false); //Inflates fragment
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class); //Hook up view model

        searchList = new ArrayList<>(); //Create new array list
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext()); //Setup linear layout for recycler
        binding.recyclerView.setLayoutManager(layoutManager); //Pass layout manage to recycler
        searchAdapter = new SearchAdapter(getContext(), searchList); //Pass search result for recycler adpater
        binding.recyclerView.setAdapter(searchAdapter); //Set adapter for recycler view
        searchAdapter.setClickListener(this); //Setup on click listener event to search adapters click event


        //Observe search data in order to keep data on changes to recycler or view
        viewModel.getSearchData().observe(getViewLifecycleOwner(), stockData -> {
            searchList.clear(); //Clears search list
            searchList.addAll(stockData); //Adds stock data to search list
            searchAdapter.notifyDataSetChanged(); //Notify adapter of change
        });

        //Setup event listener for search view
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if(runnable != null){ //Checks if runnable isn't null
                    handler.removeCallbacks(runnable); //Removes runnable from handler
                }
                runnable = () -> viewModel.Refresh(newText); //Calls Refresh method in view model through runnable
                handler.postDelayed(runnable, 200); //Delays post event till 2 after user stops typing
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.Refresh(query); //Calls refresh method in view model
                return true;
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();

    }

    @Override
    public void onCLick(View v, int pos) {
        //Check if name is equal to any of these values to prevent fragment from being called
        if(searchList.get(pos).getName().equals("No City Found") || searchList.get(pos).getName().equals("Loading...")
                || searchList.get(pos).getName().equals("ERROR: API CALL FAILED PLEASE TRY AGAIN!")){
            return; //Returns and does nothing
        }
        FragmentManager fn = getActivity().getSupportFragmentManager(); //Get fragment manager
        FragmentTransaction ft = fn.beginTransaction(); //Begin fragment transaction
        ft.replace(R.id.fragment, CityWeatherFragment.passArgument(searchList.get(pos).getName())); //Replaces element with main id with searchFragment
        ft.commit(); //Commits transaction
    }
}
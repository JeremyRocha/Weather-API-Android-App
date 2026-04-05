package com.example.assignmenttwoweatherapi.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttwoweatherapi.R;
import com.example.assignmenttwoweatherapi.model.SearchModel;
import com.example.assignmenttwoweatherapi.utils.ItemClickListener;
import com.example.assignmenttwoweatherapi.view.RecycleViewHolder;

import java.util.List;

//View holder for recycler
public class SearchAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    List<SearchModel> searchList; //Initialize variable for search results
    Context context; //Initial context variable
    public ItemClickListener clickListener; //Initialize click listener

    //Constructor for class. Passes context and search list and assigns them to variable
    public SearchAdapter(Context context, List<SearchModel> searchList) {
        this.context = context;
        this.searchList = searchList;
    }


    //Setter for click listener
    public void setClickListener(ItemClickListener myListener){
        this.clickListener = myListener;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false); //Inflate item view for recycler

        RecycleViewHolder viewHolder = new RecycleViewHolder(searchListView, clickListener); //Setup adapter passing view and click listener to adater

        return viewHolder; //Returns view holder
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        SearchModel searchModel = searchList.get(position); //Gets object from search at position

        holder.getName().setText(searchModel.getName()); //Sets value of name in item view in recycler view
        holder.getCountry().setText(searchModel.getCountry()); //Sets value of country in item view in recycler view
        holder.getRegion().setText(searchModel.getRegion()); //Sets value of region in item view in recycler view
        holder.getImageView().setImageResource(searchModel.getImage()); //Sets value of image in item view in recycler view
    }

    //Returns item count of search list
    @Override
    public int getItemCount() {
        return searchList.size();
    }
}

package com.example.assignmenttwoweatherapi.view;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttwoweatherapi.R;
import com.example.assignmenttwoweatherapi.utils.ItemClickListener;

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView; //Initialize variable for image view
    TextView name; //Initialize name text view
    TextView region; //Initialize region text view
    TextView country; //Initialize country text view
    ItemClickListener clickListener; //Initialize event listen

    //Getter for variable
    public ImageView getImageView() {
        return imageView;
    }

    public TextView getName() {
        return name;
    }

    public TextView getCountry() {
        return country;
    }

    public TextView getRegion() {
        return region;
    }

    //Recycler view holder
    public RecycleViewHolder(@NonNull View itemView, ItemClickListener clickListener) {
        super(itemView); //Set item view

        imageView = itemView.findViewById(R.id.imageview); //Set image
        name = itemView.findViewById(R.id.title_txt); //Set name
        region = itemView.findViewById(R.id.region_text); //Set region
        country = itemView.findViewById(R.id.country_text); //Set country

        this.clickListener = clickListener; //Set click listener

        //Setup click event for each item
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onCLick(view, getAbsoluteAdapterPosition());
            }
        });



    }
}

package com.example.cinemaapp.adapter;

import android.widget.ImageView;

import com.example.cinemaapp.model.Movie;

public interface MovieItemClickListener {
    void onMovieClick(Movie movie, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity
}

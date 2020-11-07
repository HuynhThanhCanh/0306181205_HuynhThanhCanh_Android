package com.example.cinemaapp.ui;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.adapter.MovieItemClickListener;
import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.SlidePagerAdapter;
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.Slide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class movieList extends AppCompatActivity implements MovieItemClickListener {
private List<Slide>lstslides;
private ViewPager slidepager;
private TabLayout indicator;
private RecyclerView MoviesRV;
private RecyclerView MoviesRV1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        slidepager=findViewById(R.id.slider_pager);
        indicator =findViewById(R.id.indicator);
        MoviesRV=findViewById(R.id.Rv_movies);
        MoviesRV1=findViewById(R.id.Rv_movies1);
        lstslides= new ArrayList<>();
        lstslides.add(new Slide(R.drawable.slide1,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.slide2,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.slide1,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.slide2,"slide Title /nmore text here"));
        SlidePagerAdapter adapter = new SlidePagerAdapter(this,lstslides);
        slidepager.setAdapter(adapter);

        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new movieList.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(slidepager,true);

        //setup RecylerView Phim đang chiếu
        List<Movie>lstMovies=new ArrayList<>();
        lstMovies.add(new Movie("Moana",R.drawable.moana,R.drawable.moana));
        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.blackp));
        lstMovies.add(new Movie("Mulan",R.drawable.mulan,R.drawable.mulan));
        lstMovies.add(new Movie("Avanger",R.drawable.anvanger,R.drawable.anvanger));
        lstMovies.add(new Movie("Ròm",R.drawable.rom,R.drawable.rom));
        lstMovies.add(new Movie("Tiệc trăng máu",R.drawable.tiectrangmau,R.drawable.tiectrangmau));
        MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies,this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        //setup RecylerView Phim đang chiếu
        List<Movie>lstMovies1=new ArrayList<>();
        lstMovies.add(new Movie("Moana",R.drawable.moana,R.drawable.moana));
        lstMovies.add(new Movie("Black P",R.drawable.blackp,R.drawable.blackp));
        lstMovies.add(new Movie("Mulan",R.drawable.mulan,R.drawable.mulan));
        lstMovies.add(new Movie("Avanger",R.drawable.anvanger,R.drawable.anvanger));
        lstMovies.add(new Movie("Ròm",R.drawable.rom,R.drawable.rom));
        lstMovies.add(new Movie("Tiệc trăng máu",R.drawable.tiectrangmau,R.drawable.tiectrangmau));
        MovieAdapter movieAdapter1 = new MovieAdapter(this,lstMovies1,this);
        MoviesRV1.setAdapter(movieAdapter);
        MoviesRV1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
    }

    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            movieList.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slidepager.getCurrentItem()<lstslides.size()-1)
                    {
                        slidepager.setCurrentItem(slidepager.getCurrentItem()+1);
                    }
                    else

                        slidepager.setCurrentItem(0);
                }
            });
        }
    }
}
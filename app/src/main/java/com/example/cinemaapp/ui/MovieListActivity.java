package com.example.cinemaapp.ui;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.model.MovieItemClickListener;
import com.example.cinemaapp.adapter.SlidePagerAdapter;
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.Slide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MovieListActivity extends AppCompatActivity implements MovieItemClickListener {
    //Đầu tiên khai báo RecyclerView
private List<Slide>lstslides;
private ViewPager slidepager;
private TabLayout indicator;
private RecyclerView MoviesRV;
private RecyclerView MoviesRV1;
private EditText searchInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        slidepager=findViewById(R.id.slider_pager);
        indicator =findViewById(R.id.indicator);
        MoviesRV=findViewById(R.id.Rv_movies);
        MoviesRV1=findViewById(R.id.Rv_movies1);
        //input search
        searchInput=findViewById(R.id.inputSearch);
        searchInput.setSelected(false);

        lstslides= new ArrayList<>();
        lstslides.add(new Slide(R.drawable.mulan,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.spidercover,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.anvanger,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.rom,"slide Title /nmore text here"));
        SlidePagerAdapter adapter = new SlidePagerAdapter(this,lstslides);
        slidepager.setAdapter(adapter);
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MovieListActivity.SliderTimer(),4000,6000);
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

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                movieAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
       /* Intent intent = new Intent(this, MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());

        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(movieList.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this,"Bạn vừa chọn Phim " + movie.getTitle(),Toast.LENGTH_LONG).show();*/
    }

    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MovieListActivity.this.runOnUiThread(new Runnable() {
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
package com.example.cinemaapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ThongTinPhimActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView MovieThumbnaiImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    public static Drawable thisImg;

    public Movie movie = new Movie();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phim);
        XuLyMuave();
        inViews();
        thisImg=MovieThumbnaiImg.getDrawable();
    }

     void XuLyMuave() {
         findViewById(R.id.btndatve).setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), GiaodienActivity.class);

                 startActivity(intent);
             }
         });
    }

    private  void inViews()
    {
        play_fab=findViewById(R.id.play_fab);
        String movieTitle =getIntent().getExtras().getString("title");
        int imageResoureId=getIntent().getExtras().getInt("imgURL");
        int imageCover=getIntent().getExtras().getInt("imgCover");
        MovieThumbnaiImg=findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResoureId).into(MovieThumbnaiImg);
        MovieThumbnaiImg.setImageResource(imageResoureId);
        MovieCoverImg=findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);
        tv_title=findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        MovieThumbnaiImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        tv_title.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        movie.setCoverPhoto(imageCover);
    }

    public void play_trailer(View view) {
        play_fab=findViewById(R.id.play_fab);
        Intent intent = new Intent(this, TrailerActivity.class);
        startActivity(intent);
    }

}
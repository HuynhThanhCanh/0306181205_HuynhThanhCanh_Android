package com.example.cinemaapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.GiaodienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class ThongTinPhimActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView MovieThumbnaiImg,MovieCoverImg;
    private TextView tv_title,tv_description,sao,genrename,daoDien;
    private FloatingActionButton play_fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phim);
        //Của fragment
        //tabLayout=findViewById(R.id.tab_layout);
        //viewPager=findViewById(R.id.view_page);
        //GiaodienAdapter giaodienAdapter = new GiaodienAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        //viewPager.setAdapter(giaodienAdapter);
        // tabLayout.setupWithViewPager(viewPager);

        inViews();
    }
    void inViews()
    {
        play_fab=findViewById(R.id.play_fab);
        String movieTitle =getIntent().getExtras().getString("title");
        String imageResoureId=getIntent().getExtras().getString("imgURL");
        String imageCover=getIntent().getExtras().getString("imgCover");
        String rating=getIntent().getExtras().getString("rating");
        String genre=getIntent().getExtras().getString("genre");
        String Directors=getIntent().getExtras().getString("Directors");
        MovieThumbnaiImg=findViewById(R.id.detail_movie_img);
      Glide.with(this).load(imageResoureId).into(MovieThumbnaiImg);
       MovieCoverImg=findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);

       tv_title=findViewById(R.id.detail_movie_title);
       tv_title.setText(movieTitle);

       sao=findViewById(R.id.so_sao);
       sao.setText(rating);
        genrename=findViewById(R.id.ten_the_loai);
        genrename.setText(genre);
        daoDien=findViewById(R.id.ten_dao_dien);
        daoDien.setText(Directors);
       MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        tv_title.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

    }

    public void play_trailer(View view) {
        play_fab=findViewById(R.id.play_fab);
        Intent intent = new Intent(this, TrailerActivity.class);
        startActivity(intent);
    }
}
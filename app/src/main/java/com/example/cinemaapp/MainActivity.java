package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int stt =0;
    ViewPager viewPager;
    ViewPager viewPager2;
    Adapter adapter;
    private Handler sliderHandler = new Handler();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;
    List<Model> models2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.img_1,"Tiec Trang Mau","Hai huoc"));
        models.add(new Model(R.drawable.img_2,"365 ngay yeu anh","tinh cam"));
        models.add(new Model(R.drawable.img_3,"minions","hoat hinh"));
        models.add(new Model(R.drawable.img_2,"EngGame","Hanh Dong"));
        models.add(new Model(R.drawable.img_3,"Em chua 18","Tam ly"));
        adapter = new Adapter(models,this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPadding(100,0,100,0);
        Integer[] colors_temp ={getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)};

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position<(adapter.getCount()-1) &&position <(colors.length-1))
//                {
//                    viewPager.setBackgroundColor(
//
//                            (Integer) argbEvaluator
//                                    .evaluate(positionOffset
//                                            ,colors[position]
//                                            ,colors[position+1])
//                    );
//
//                }
//                else
//                {
//                    viewPager.setBackgroundColor(colors[colors.length-1]);
//                }
            }

            @Override
            public void onPageSelected(int position) {
                 stt=position+1;
                 sliderHandler.removeCallbacks(sliderRunnable);
                 sliderHandler.postDelayed(sliderRunnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
            initslide(models,viewPager2);

    }

    public void initslide(List<Model> dsModel,ViewPager slideViewPager)
    {
        adapter = new Adapter(dsModel,this);
        slideViewPager = findViewById(R.id.viewPager2);
        slideViewPager.setAdapter(adapter);
        slideViewPager.setClipChildren(false);
        slideViewPager.setOffscreenPageLimit(3);
        slideViewPager.setPadding(100,0,100,0);
        Integer[] colors_temp ={getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)};

        colors = colors_temp;

        slideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position<(adapter.getCount()-1) &&position <(colors.length-1))
//                {
//                    viewPager.setBackgroundColor(
//
//                            (Integer) argbEvaluator
//                                    .evaluate(positionOffset
//                                            ,colors[position]
//                                            ,colors[position+1])
//                    );
//
//                }
//                else
//                {
//                    viewPager.setBackgroundColor(colors[colors.length-1]);
//                }
            }

            @Override
            public void onPageSelected(int position) {
                stt=position+1;
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    };
    private Runnable sliderRunnable2 = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    public void xem_chi_tiet(View view) {
        String tb = "Ban da chon phim"+Integer.toString(stt);
        Toast.makeText(MainActivity.this,tb,Toast.LENGTH_LONG).show();
    }
}
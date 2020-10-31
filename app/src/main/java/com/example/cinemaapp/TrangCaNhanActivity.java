package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class TrangCaNhanActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_trang_ca_nhan);
        mViewPager.setAdapter(new TrangCaNhanAdapter(getSupportFragmentManager()));
        TabLayout tablayout = (TabLayout) findViewById(R.id.tl_trang_ca_nhan);
        tablayout.setupWithViewPager(mViewPager);
    }
}
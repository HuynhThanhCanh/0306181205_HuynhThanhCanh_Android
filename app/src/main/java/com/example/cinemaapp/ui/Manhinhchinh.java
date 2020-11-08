package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Manhinhchinh extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_page);
        GiaodienAdapter giaodienAdapter = new GiaodienAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(giaodienAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
package com.example.cinemaapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.GiaodienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class GiaodienActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        GiaodienAdapter giaodienAdapter = new GiaodienAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(giaodienAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

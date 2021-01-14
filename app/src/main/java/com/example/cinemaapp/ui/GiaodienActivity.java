package com.example.cinemaapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.GiaodienAdapter;
import com.example.cinemaapp.fragment.LichChieuFragment;
import com.google.android.material.tabs.TabLayout;

public class GiaodienActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public int hinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);

        Intent intent = getIntent();
       // hinh = intent.getIntExtra("caihinhnhonhoxinhxinh", 0);

        Bundle bundle = new Bundle();
        bundle.putInt("hinh", hinh);

        // set Fragmentclass Arguments
        LichChieuFragment fragobj = new LichChieuFragment();
        fragobj.setArguments(bundle);

        GiaodienAdapter giaodienAdapter = new GiaodienAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(giaodienAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

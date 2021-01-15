package com.example.cinemaapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.TrangCaNhanAdapter;
import com.example.cinemaapp.fragment.TrangCaNhan_ThongTinFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class TrangCaNhanActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);
        //
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_trang_ca_nhan);
        mViewPager.setAdapter(new TrangCaNhanAdapter(getSupportFragmentManager()));
        TabLayout tablayout = (TabLayout) findViewById(R.id.tl_trang_ca_nhan);
        tablayout.setupWithViewPager(mViewPager);
    }
}
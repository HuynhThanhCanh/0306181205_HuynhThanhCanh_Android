package com.example.cinemaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.adapter.RecentsAdapter;
import com.example.cinemaapp.adapter.TopPlacesAdapter;
import com.example.cinemaapp.model.RecentsData;
import com.example.cinemaapp.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class listMovie extends AppCompatActivity {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan1));
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan2));
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan3));
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan4));
        recentsDataList.add(new RecentsData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));
        topPlacesDataList.add(new TopPlacesData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));
        topPlacesDataList.add(new TopPlacesData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));
        topPlacesDataList.add(new TopPlacesData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));
        topPlacesDataList.add(new TopPlacesData("Mulan","Việt Nam","From 100.000 VNĐ",R.drawable.mulan));

        setTopPlacesRecycler(topPlacesDataList);
    }
    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }
    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}
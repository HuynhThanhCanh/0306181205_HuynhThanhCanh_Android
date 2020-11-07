package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class GiaodienAdapter extends FragmentStatePagerAdapter {
    public GiaodienAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Thongtinphim();
            case 1: return new Lichchieu();
            case 2: return  new Danhgia();
            default: return  new Thongtinphim();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0: title ="Thông tin phim";
            break;
            case 1: title ="Lịch chiếu";
            break;
            case 2: title="Đánh giá";
            break;
        }
        return super.getPageTitle(position);
    }
}

package com.example.cinemaapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cinemaapp.fragment.LichChieuFragment;

import com.example.cinemaapp.fragment.DanhGiaFragment;

public class GiaodienAdapter extends FragmentStatePagerAdapter {


    public GiaodienAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
           // case 0: return new ThongTinPhimFragment();
            case 1: return new LichChieuFragment();
            case 2: return  new DanhGiaFragment();
            default: return  new LichChieuFragment();

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

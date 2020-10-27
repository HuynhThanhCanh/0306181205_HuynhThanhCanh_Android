package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TrangCaNhanAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Thông tin", "Giao dịch"};
    private TrangCaNhan_ThongTin mFirstFragment;
    private TrangCaNhan_LichSuGiaoDich mSecondFragment;

    public TrangCaNhanAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mFirstFragment = new TrangCaNhan_ThongTin();
        mSecondFragment = new TrangCaNhan_LichSuGiaoDich();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return mFirstFragment;
        }else if(position == 1){
            return mSecondFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}

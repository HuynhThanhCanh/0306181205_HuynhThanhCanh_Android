package com.example.cinemaapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cinemaapp.fragment.TrangCaNhan_GiaoDichFragment;
import com.example.cinemaapp.fragment.TrangCaNhan_ThongTinFragment;

public class TrangCaNhanAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Thông tin", "Giao dịch"};
    private TrangCaNhan_ThongTinFragment mFirstFragment;
    private TrangCaNhan_GiaoDichFragment mSecondFragment;

    public TrangCaNhanAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mFirstFragment = new TrangCaNhan_ThongTinFragment();
        mSecondFragment = new TrangCaNhan_GiaoDichFragment();
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

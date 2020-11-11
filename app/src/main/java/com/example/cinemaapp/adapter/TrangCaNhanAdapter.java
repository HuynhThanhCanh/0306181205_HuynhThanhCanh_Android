package com.example.cinemaapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cinemaapp.ui.TrangCaNhan_GiaoDichActivity;
import com.example.cinemaapp.ui.TrangCaNhan_ThongTinActivity;

public class TrangCaNhanAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Thông tin", "Giao dịch"};
    private TrangCaNhan_ThongTinActivity mFirstFragment;
    private TrangCaNhan_GiaoDichActivity mSecondFragment;

    public TrangCaNhanAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mFirstFragment = new TrangCaNhan_ThongTinActivity();
        mSecondFragment = new TrangCaNhan_GiaoDichActivity();
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

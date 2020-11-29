package com.example.cinemaapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinemaapp.R;
import com.example.cinemaapp.ui.LichSuChiTieuActivity;
import com.example.cinemaapp.ui.LichSuGiaoDichActivity;

public class TrangCaNhan_GiaoDichFragment extends Fragment implements View.OnClickListener {
    private View mRootView;
    private  Button btnLichSuGiaoDich;
    private  Button btnLichSuChiTieu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trang_ca_nhan__giao_dich, container, false);

        //set setOnClickListener for btnLichSuGiaoDich
        btnLichSuGiaoDich = (Button) mRootView.findViewById(R.id.btn_LichSuGiaoDich);
        btnLichSuGiaoDich.setOnClickListener(this);
        //set setOnClickListener for btnLichSuChiTieu
        btnLichSuChiTieu = (Button) mRootView.findViewById(R.id.btn_ChiTieu);
        btnLichSuChiTieu.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_LichSuGiaoDich: {
                Intent intent = new Intent(getActivity(), LichSuGiaoDichActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_ChiTieu:{
                Intent intent = new Intent(getActivity(), LichSuChiTieuActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
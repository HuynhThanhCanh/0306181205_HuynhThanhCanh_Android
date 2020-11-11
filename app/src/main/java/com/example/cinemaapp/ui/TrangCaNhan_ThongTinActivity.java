package com.example.cinemaapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cinemaapp.R;

import java.util.ArrayList;

public class TrangCaNhan_ThongTinActivity extends Fragment {
    private View mRootView;
    private ArrayList list;
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_trang_ca_nhan__thong_tin, container, false);
        SpinnerCity();
        SpinnerDistrict();
        SpinnerWard();
        return  mRootView;
    }

    public void SpinnerCity(){
        list = new ArrayList<>();
        list.add("Hà Nội");
        list.add("Thành phố Hồ Chí Minh");
        list.add("Đà Đẵng");
        list.add("Nha Trang");
        list.add("Đà Lạt");
        list.add("Bình Thuận");
        list.add("Bến Tre");
        list.add("Tiền Giang");
        list.add("Hà Tĩnh");
        list.add("Quãng Ngãi");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_City);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }

    public void SpinnerDistrict(){
        list = new ArrayList<>();
        list.add("Quận 1");
        list.add("Quận 2");
        list.add("Quận 3");
        list.add("Quận 4");
        list.add("Quận 5");
        list.add("Quận 6");
        list.add("Quận 7");
        list.add("Quận 8");
        list.add("Quận 9");
        list.add("Quận 10");
        list.add("Quận 11");
        list.add("Quận 12");
        list.add("Quận Bình Thạnh");
        list.add("Quận Gò Vấp");
        list.add("Quận Thủ Đức");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_District);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }

    public void SpinnerWard(){
        list = new ArrayList<>();
        list.add("Phường 1");
        list.add("Phường 2");
        list.add("Phường 3");
        list.add("Phường 4");
        list.add("Phường 5");
        list.add("Phường 6");
        list.add("Phường 7");
        list.add("Phường 8");
        list.add("Phường 9");
        list.add("Phường 10");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_Ward);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }
}
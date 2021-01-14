package com.example.cinemaapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.cinemaapp.R;
import java.util.ArrayList;

public class TrangCaNhan_ThongTinFragment extends Fragment {
    private View mRootView;
    private ArrayList list;
    //Declare Control
    TextView txtFullName;
    EditText txtUserName;
    EditText txtPhoneNumber;
    EditText txtUserNameDateOfBirth;
    EditText txtEmail;
    EditText txtFullAddress;
    Button btnCapNhat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trang_ca_nhan__thong_tin, container, false);
        //Start set control
        txtFullName = mRootView.findViewById(R.id.tv_Name);
        txtUserName = mRootView.findViewById(R.id.edtext_Name);
        txtPhoneNumber = mRootView.findViewById(R.id.edtext_Phone);
        txtUserNameDateOfBirth = mRootView.findViewById(R.id.edtext_BirthDay);
        txtEmail =  mRootView.findViewById(R.id.edtext_Email);
        txtFullAddress = mRootView.findViewById(R.id.edText_Address);
        btnCapNhat = mRootView.findViewById(R.id.btn_CapNhat);

        btnCapNhat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//                try {
//                    GetAPI();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
        return  mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
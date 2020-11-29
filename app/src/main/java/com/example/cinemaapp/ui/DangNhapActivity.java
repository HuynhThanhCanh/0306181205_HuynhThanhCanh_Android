package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cinemaapp.R;

public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
        //actionBar.hide(); ẩn tên app
        //actionBar.setTitle("Đăng nhập");//đặt tên app
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home://R.id.home là mặc định ID của nút mũi tên quay lại
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDangKyActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), DangKyActivity.class);
        startActivity(intent);
    }
}
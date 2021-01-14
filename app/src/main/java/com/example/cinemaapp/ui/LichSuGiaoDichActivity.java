package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cinemaapp.R;

public class LichSuGiaoDichActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
//        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
//        //actionBar.hide(); ẩn tên app
//        actionBar.setTitle("Lịch sử giao dịch");//đặt tên app
//        String title = actionBar.getTitle().toString();
//        actionBar.setDisplayHomeAsUpEnabled(true);//dấu mũi tên
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));

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

//    public void back(View view) {
//        this.finish();
//    }
}
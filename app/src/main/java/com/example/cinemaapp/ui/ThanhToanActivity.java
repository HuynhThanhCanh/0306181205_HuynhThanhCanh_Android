package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cinemaapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ThanhToanActivity extends AppCompatActivity {

    Integer tongGiaTien, tongSoGhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
//        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
//        actionBar.hide(); //ẩn tên app
//        actionBar.setTitle("Thanh toán");//đặt tên app
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên

////      Bat dau code cua trang ne nha
//        Intent intent = getIntent();
//        tongGiaTien = intent.getIntExtra("tongtien", 0);
//        tongSoGhe = intent.getIntExtra("soghe", 0);
//
//        TextView txtSo = (TextView) findViewById(R.id.txtso);
//        TextView txtTong = (TextView) findViewById(R.id.txtTTTongTien);
//
//        NumberFormat format = new DecimalFormat("#,###");
//        String finalTongTien = format.format(tongGiaTien);
//
//        txtSo.setText(tongSoGhe.toString());
//        txtTong.setText(finalTongTien.toString() + "VNĐ");
////        Ket thuc phan cua Trang

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
}
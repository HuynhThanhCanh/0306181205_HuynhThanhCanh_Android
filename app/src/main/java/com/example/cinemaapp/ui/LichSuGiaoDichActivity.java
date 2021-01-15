package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.VeAdapter;
import com.example.cinemaapp.api.APIGettingVe;
import com.example.cinemaapp.model.Ve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class LichSuGiaoDichActivity extends AppCompatActivity{
    private LinkedList<Ve> listVe;
    private RecyclerView mRecylerView;
    private VeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_giao_dich);
        try {
            showData(MainActivity.MaThanhVien);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showData(String value) throws ExecutionException, InterruptedException, JSONException, JSONException {
        String list = new APIGettingVe(this).execute(value).get();

        JSONArray jr = new JSONArray(list);
        int len = jr.length();
        listVe = new LinkedList<Ve>();
        for(int i = 0; i < len; i++){
            JSONObject jb = (JSONObject)jr.getJSONObject(i);
            String tenPhim = jb.getString("TenPhim");
            String chiNhanh = jb.getString("TenChiNhanh");
            String thoiGianMua = jb.getString("ThoiGianMua");
            String ghe = jb.getString("MaGhe");
            Double thanhTien = Double.parseDouble(jb.getString("ThanhTien"));
            Ve tv = new Ve(tenPhim, chiNhanh, thanhTien, ghe, 5, thoiGianMua);
            this.listVe.addLast(tv);
        }
        mRecylerView = findViewById(R.id.recy_listDsVe);
        mAdapter = new VeAdapter(this, listVe);
        mRecylerView.setAdapter(mAdapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
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
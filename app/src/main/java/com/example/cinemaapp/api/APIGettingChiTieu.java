package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cinemaapp.model.User;

public class APIGettingChiTieu extends AsyncTask<String, String, String> {
    private Context m_con;
    public User user;
    public APIGettingChiTieu(Context con){
        m_con = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(m_con, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... value) {
        if(value[0] == "getForYear"){
            return APIChiTieu.getTongChiTieuTrongNam(value[1], value[2]);
        }else if(value[0] == "getForDate"){
            return APIChiTieu.getTongChiTieuTrongKhoangThoiGian(value[1], value[2], value[3]);
        }else{
            return "Chưa cài đặt phương thức!";
        }
    }
}

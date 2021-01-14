package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cinemaapp.model.User;

public class APIGettingUser extends AsyncTask<String, String, String> {
    private Context m_con;
    public User user;
    public APIGettingUser(Context con){
        m_con = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(m_con, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... value) {
        if(value[1] == "update"){
            return APIUser.updateUser(value[0]);
        }else{
            return APIUser.updateUser(value[0]);
        }
    }
}

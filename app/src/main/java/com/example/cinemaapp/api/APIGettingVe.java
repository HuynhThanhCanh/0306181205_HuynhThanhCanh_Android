package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class APIGettingVe extends AsyncTask<String, String, String> {
    private Context m_con;
    public APIGettingVe(Context con){
        m_con = con;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(m_con, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... value) {
        return APIVe.getVe(value[0]);
    }
}

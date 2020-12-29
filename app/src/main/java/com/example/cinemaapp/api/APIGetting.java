package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.dynamic.IFragmentWrapper;

public class APIGetting extends AsyncTask<String , String ,  String> {
    Context context;

    public APIGetting(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        if (strings[0].equals("true"))
        {

            return  APIMovieDangChieu.getMovieDangChieu();
        }

     return  APIMovieSapChieu.getMovieSapChieu();

    }
}

package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;

import com.example.cinemaapp.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APISearchPhim extends AsyncTask<String,String,String> {
    Context context;

    public  APISearchPhim(Context mCon)
    {
        this.context=mCon;
    }



    public static String searchWord(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL requestURL = new URL(MainActivity.HostDomain+"api/phim");
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            if (builder.length() == 0) {
                return null;
            }
            result = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    protected String doInBackground(String... strings) {
        return APISearchPhim.searchWord();
    }
}

package com.example.cinemaapp.api;

import android.os.AsyncTask;

import com.example.cinemaapp.model.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIDangNhap extends AsyncTask<Users,String,String> {
    @Override
    protected String doInBackground(Users... users) {
        return DangNhap(users[0]);
    }
    public  String DangNhap(Users users)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            //URL requestURL = new URL("http://192.168.1.108/dictionary/api.php?word="+tuVung.getWord()+"&definition="+tuVung.getDefinition()+"&image="+tuVung.getImage());
            URL requestURL = new URL("http://192.168.1.12:8080/api/loginApp?User="+users.getEmail()+"&Pass="+users.getPass());
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
}

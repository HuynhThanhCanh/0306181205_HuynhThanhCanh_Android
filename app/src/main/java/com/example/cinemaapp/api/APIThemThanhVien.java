package com.example.cinemaapp.api;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cinemaapp.model.User;
import com.example.cinemaapp.model.Users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIThemThanhVien  extends AsyncTask<Users,String,String> {
    @Override
    protected String doInBackground(Users... users) {
        return ThemThanhVien(users[0]);
    }
    private Context m_con;
    public  APIThemThanhVien(Context con)
    {
        m_con = con;
    }
    public   String ThemThanhVien(Users users)
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            //URL requestURL = new URL("http://192.168.1.108/dictionary/api.php?word="+tuVung.getWord()+"&definition="+tuVung.getDefinition()+"&image="+tuVung.getImage());
            URL requestURL = new URL("http://192.168.131.35:8080/api/savethanhvien?HoTenTV="+users.getHoTenTV()+"&NgaySinh="+users.getNgaySinh()+"&SDT="+users.getSDT()+"&Email="+users.getEmail()+"&Password="+users.getPass()+"&DiaChi="+users.getDiaChi());
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

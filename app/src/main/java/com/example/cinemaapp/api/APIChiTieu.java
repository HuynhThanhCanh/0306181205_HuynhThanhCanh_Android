package com.example.cinemaapp.api;

import com.example.cinemaapp.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIChiTieu {
    static String getTongChiTieuTrongNam(String nam, String maTV) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL requestURL = new URL(MainActivity.HostDomain+"api/tong-chi-tieu-trong-nam?nam=" + nam + "&maTV=" + maTV);
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
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

    static String getTongChiTieuTrongKhoangThoiGian(String ngayBatDau, String ngayKetThuc ,String maTV) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL requestURL = new URL(MainActivity.HostDomain+"api/tong-chi-tieu-trong-khoang-thoi-gian?ngayBatDau=" + ngayBatDau + "&ngayKetThuc=" + ngayKetThuc +"&maTV=" + maTV);
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
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

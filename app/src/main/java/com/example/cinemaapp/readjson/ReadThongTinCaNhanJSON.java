/*
 * Author : Huỳnh Thanh Cảnh
 * Date create: 12/11/2020 1:20 PM
 * */

package com.example.cinemaapp.readjson;

import android.content.Context;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Address;
import com.example.cinemaapp.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class ReadThongTinCaNhanJSON {

   public static LinkedList<String> mlist = new LinkedList<String>();
    //Đọc nội dung file thong_tin_ca_nhan.json
    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static User readThongTinCaNhanJsonFile(Context context) throws IOException, JSONException {
        //Đọc nội dung text của file thong_tin_ca_nhan.json
        String jsonText = readText(context, R.raw.thong_tin_ca_nhan);

        //tạo đối tượng JSONObject gốc, mô tả toàn bộ tài liệu json
        JSONObject jsonRoot = new JSONObject(jsonText);

        User user = new User();
        user.setFullName(jsonRoot.getJSONObject("user").getString("full_name"));
        user.setUserName(jsonRoot.getJSONObject("user").getString("username"));
        user.setAvatar(jsonRoot.getJSONObject("user").getString("avatar"));
        user.setPhoneNumber(jsonRoot.getJSONObject("user").getString("phone_number"));
        user.setDayOfBirth(jsonRoot.getJSONObject("user").getString("day_of_birth"));
        user.setEmail(jsonRoot.getJSONObject("user").getString("email"));
        user.setSex(Integer.parseInt(jsonRoot.getJSONObject("user").getString("sex")));

        Address address = new Address();
        address.setCity(jsonRoot.getJSONObject("user").getJSONObject("address").getString("city"));
        address.setDistrict(jsonRoot.getJSONObject("user").getJSONObject("address").getString("district"));
        address.setAward(jsonRoot.getJSONObject("user").getJSONObject("address").getString("ward"));

        user.setAddress(address);
        return user;
    }
}

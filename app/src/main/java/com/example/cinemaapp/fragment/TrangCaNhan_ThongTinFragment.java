package com.example.cinemaapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.Sqlite.Database;
import com.example.cinemaapp.api.APIGettingUser;
import com.example.cinemaapp.model.Users;
import com.example.cinemaapp.ui.MainActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TrangCaNhan_ThongTinFragment extends Fragment implements View.OnClickListener {
    private View mRootView;
    private ArrayList list;
    //Declare Control
    private TextView hoTenTVTitle;
    private EditText hoTenTV;
    private EditText sdt;
    private EditText ngaySinh;
    private EditText email;
    private EditText diaChi;
    Button btnCapNhat;

    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trang_ca_nhan__thong_tin, container, false);
        //
        hoTenTVTitle = (TextView)mRootView.findViewById(R.id.txt_NameTitle);
        hoTenTV = (EditText)mRootView.findViewById(R.id.txt_Name);
        sdt = (EditText)mRootView.findViewById(R.id.txt_Phone);
        ngaySinh = (EditText)mRootView.findViewById(R.id.txt_BirthDay);
        email = (EditText)mRootView.findViewById(R.id.txt_Email);
        diaChi = (EditText)mRootView.findViewById(R.id.txt_Address);
        btnCapNhat = mRootView.findViewById(R.id.btn_CapNhat);
        //
        try {
            showData(MainActivity.MaThanhVien);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnCapNhat.setOnClickListener(this);
        return  mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showData(String value) throws ExecutionException, InterruptedException, JSONException, JSONException {
        String list = new APIGettingUser(mRootView.getContext()).execute("get", MainActivity.MaThanhVien).get();

        JSONArray jr = new JSONArray(list);
        int len = jr.length();
        Users user = new Users();
        for(int i = 0; i < len; i++){
            JSONObject jb = (JSONObject)jr.getJSONObject(i);
            String hoTenTV = jb.getString("HoTenTV");
            String ngaySinh = jb.getString("NgaySinh");
            String sdt = jb.getString("SDT");
            String email = jb.getString("Email");
            String diaChi = jb.getString("DiaChi");
            user.setHoTenTV(hoTenTV);
            user.setNgaySinh(ngaySinh);
            user.setSDT(sdt);
            user.setEmail(email);
            user.setDiaChi(diaChi);
        }
        //Assign data
        hoTenTVTitle.setText(user.getHoTenTV());
        hoTenTV.setText(user.getHoTenTV());
        ngaySinh.setText(user.getNgaySinh());
        sdt.setText(user.getSDT());
        diaChi.setText(user.getDiaChi());
        email.setText(user.getEmail());
    }

    public void updateData() throws ExecutionException, InterruptedException, JSONException, JSONException {
        String hoTenTV = this.hoTenTV.getText().toString();
        String sdt = this.sdt.getText().toString();
        String diaChi = this.diaChi.getText().toString();
        String a = new APIGettingUser(mRootView.getContext()).execute("update", MainActivity.MaThanhVien, hoTenTV, sdt, diaChi).get();
        Boolean check = a.equals("1");
        if(a.equals("1")){
            Toast.makeText(mRootView.getContext(), "Cập nhật thành công!",Toast.LENGTH_LONG).show();
            Users user = new Users();
            user.setHoTenTV(hoTenTV);
            user.setId(Integer.parseInt(MainActivity.MaThanhVien));
            user.setEmail(email.getText().toString());
            Gson gson = new Gson();
            String jsonUser = gson.toJson(user);
            database= new Database(mRootView.getContext(),"cinema.sqlite",null,3);
            database.QueryData("INSERT INTO ThanhVien VALUES('" +jsonUser+"',1)");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_CapNhat:{
                try {
                    updateData();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
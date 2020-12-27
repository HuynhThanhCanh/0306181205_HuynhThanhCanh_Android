package com.example.cinemaapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.User;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TrangCaNhan_ThongTinFragment extends Fragment {
    private View mRootView;
    private ArrayList list;
    private Spinner spinner;
    String jsonURL="https://thongtindoanhnghiep.co/api/city";
    //Declare Control
    TextView txtFullName;
    EditText txtUserName;
    EditText txtPhoneNumber;
    EditText txtUserNameDateOfBirth;
    EditText txtEmail;
    RadioButton male;
    RadioButton female;
    EditText txtFullAddress;
    Spinner city;
    Spinner district;
    Spinner ward;
    Button btnCapNhat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_trang_ca_nhan__thong_tin, container, false);
        //Start set control
        txtFullName = mRootView.findViewById(R.id.tv_Name);
        txtUserName = mRootView.findViewById(R.id.edtext_Name);
        txtPhoneNumber = mRootView.findViewById(R.id.edtext_Phone);
        txtUserNameDateOfBirth = mRootView.findViewById(R.id.edtext_BirthDay);
        txtEmail =  mRootView.findViewById(R.id.edtext_Email);
        male = mRootView.findViewById(R.id.rad_Male);
        female = mRootView.findViewById(R.id.rad_Female);
        txtFullAddress = mRootView.findViewById(R.id.edtext_Address);
        city = mRootView.findViewById(R.id.spinner_City);
        district = mRootView.findViewById(R.id.spinner_District);
        ward = mRootView.findViewById(R.id.spinner_Ward);
        btnCapNhat = mRootView.findViewById(R.id.btn_CapNhat);
        //End set control
//        try {
//            //loadData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        btnCapNhat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    GetAPI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        SpinnerCity();
        SpinnerDistrict();
        SpinnerWard();
        return  mRootView;
    }

    public void SpinnerCity(){
        list = new ArrayList<>();
        list.add("Hà Nội");
        list.add("Thành phố Hồ Chí Minh");
        list.add("Đà Đẵng");
        list.add("Nha Trang");
        list.add("Đà Lạt");
        list.add("Bình Thuận");
        list.add("Bến Tre");
        list.add("Tiền Giang");
        list.add("Hà Tĩnh");
        list.add("Quãng Ngãi");
        list.add("Quãng Nam");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_City);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }

    public void SpinnerDistrict(){
        list = new ArrayList<>();
        list.add("Quận 1");
        list.add("Quận 2");
        list.add("Quận 3");
        list.add("Quận 4");
        list.add("Quận 5");
        list.add("Quận 6");
        list.add("Quận 7");
        list.add("Quận 8");
        list.add("Quận 9");
        list.add("Quận 10");
        list.add("Quận 11");
        list.add("Quận 12");
        list.add("Quận Bình Thạnh");
        list.add("Quận Gò Vấp");
        list.add("Quận Thủ Đức");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_District);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }

    public void SpinnerWard(){
        list = new ArrayList<>();
        list.add("Phường 1");
        list.add("Phường 2");
        list.add("Phường 3");
        list.add("Phường 4");
        list.add("Phường 5");
        list.add("Phường 6");
        list.add("Phường 7");
        list.add("Phường 8");
        list.add("Phường 9");
        list.add("Phường 10");
        spinner = (Spinner) mRootView.findViewById(R.id.spinner_Ward);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter  <String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    public void loadData() throws IOException, JSONException {
//        User user = APIGetting.readThongTinCaNhanJsonFile(getActivity());
//        txtFullName.setText(user.getFullName());
//        //
//        txtUserName.setText(user.getUserName());
//        txtPhoneNumber.setText(user.getPhoneNumber());
//        txtUserNameDateOfBirth.setText(user.getDayOfBirth());
//        txtEmail.setText(user.getEmail());
//        //
//        if(user.getSex() == 1){
//            male.setChecked(true);
//        }else if(user.getSex() == 0){
//            female.setChecked(true);
//        }else{
//            male.setChecked(false);
//            female.setChecked(false);
//        }
//    }

    public void GetAPI() throws Exception {
        //GET API
        URL urlForGetRequest = new URL("https://thongtindoanhnghiep.co/api/city");
        String readline = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("city","application/json");
        int responseCode = connection.getResponseCode();

        if(responseCode ==  HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while((readline = in.readLine()) != null){
                response.append(readline);
            }
            in.close();

            Toast.makeText(getActivity(), "JSON String Result", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "JSON NOT WORKED", Toast.LENGTH_SHORT).show();
        }
    }
}
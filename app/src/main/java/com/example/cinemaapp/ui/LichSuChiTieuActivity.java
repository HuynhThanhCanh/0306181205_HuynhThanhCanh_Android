package com.example.cinemaapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.VeAdapter;
import com.example.cinemaapp.api.APIGettingChiTieu;
import com.example.cinemaapp.api.APIGettingUser;
import com.example.cinemaapp.api.APIGettingVe;
import com.example.cinemaapp.model.Ve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class LichSuChiTieuActivity extends AppCompatActivity {
    private EditText ngayBatDau;
    private EditText ngayKetThuc;

    private TextView tongChiTieuTitle;
    private TextView tongChiTieu;
    private TextView tongChiTieuTrongMocThoiGian;
    Toolbar toolbar;

    NumberFormat format = new DecimalFormat("#,###");

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_chi_tieu);
        //
        ngayBatDau = (EditText) findViewById(R.id.edtext_StartDay);
        ngayKetThuc = (EditText) findViewById(R.id.edtext_EndDay);
        tongChiTieuTitle = (TextView)findViewById(R.id.txt_TongChiTieuTrongNam);
        tongChiTieu =(TextView)findViewById(R.id.txt_TongTien);
        tongChiTieuTrongMocThoiGian = (TextView)findViewById(R.id.txt_TongTienTheoMocThoiGian);
        toolbar = findViewById(R.id.toolbar);
        //
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        //
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        try {
            showChiTieuCuaNam(String.valueOf(year), MainActivity.MaThanhVien);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        this.finish();
    }

    public void openCalendar(View view) {
        switch (view.getId()) {
            case R.id.edtext_StartDay: {
                showCalendarDialog(ngayBatDau);
                break;
            }
            case R.id.edtext_EndDay:{
                showCalendarDialog(ngayKetThuc);
                break;
            }
        }
    }

    private void showCalendarDialog(final EditText text) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                text.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showFilterActivity(View view) {
        Intent intent = new Intent(this, LichSuGiaoDichActivity.class);
        startActivity(intent);
    }

    public void showChiTieuCuaNam(String nam, String maThanhVien) throws JSONException, ExecutionException, InterruptedException {
        String tong = new APIGettingChiTieu(this).execute("getForYear", nam, maThanhVien).get();
        Double parseDouble = Double.parseDouble(tong);
        tongChiTieuTitle.setText("Tổng chi tiêu của năm "+ nam);
        tongChiTieu.setText(format.format(parseDouble) + " VNĐ");
    }

    public void showChiTieuTrongKhoangThoiGian(String ngayBatDau, String ngayKetThuc, String maThanhVien) throws JSONException, ExecutionException, InterruptedException {
        String tong = new APIGettingChiTieu(this).execute("getForDate", ngayBatDau, ngayKetThuc, maThanhVien).get();
        Double parseDouble = Double.parseDouble(tong);
        tongChiTieuTrongMocThoiGian.setText(format.format(parseDouble) + " VNĐ");
    }

    public void xem(View view) throws InterruptedException, ExecutionException, JSONException {
        String batDau = ngayBatDau.getText().toString();
        String ketThuc = ngayKetThuc.getText().toString();
        showChiTieuTrongKhoangThoiGian(batDau, ketThuc, MainActivity.MaThanhVien);
    }
}
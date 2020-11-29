package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cinemaapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DangKyActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText starDay;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list= new ArrayList<>();
        list.add("Quận");
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
        list.add("Quận Gò Vấp");
        list.add("Quận Phú Nhuận");
        list.add("Quận Bình Thạnh");
        list.add("Quận Tân Phú");
        list.add("Quận Tân Bình");
        list.add("Quận Nhà Bè");

        //ngày sinh
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);

        starDay = (EditText) findViewById(R.id.textViewNgaySinh);
        //mũi tên quay lại
        //ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
        //actionBar.hide(); ẩn tên app
        //actionBar.setTitle("Đăng ký");//đặt tên app
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên

    }

    private void  showCalenderDialog(final EditText text)
    {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
                starDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang,ngay);
        datePickerDialog.show();

    }

    public void PopupNgay(View view) {
        switch (view.getId()){
            case R.id.textViewNgaySinh: {
                showCalenderDialog(starDay);
                break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home://R.id.home là mặc định ID của nút mũi tên quay lại
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
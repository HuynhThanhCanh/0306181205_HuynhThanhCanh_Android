package com.example.cinemaapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cinemaapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LichSuChiTieuActivity extends AppCompatActivity {
    private EditText startDay;
    private EditText endDay;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_chi_tieu);
        startDay = (EditText) findViewById(R.id.edtext_StartDay);
        endDay = (EditText) findViewById(R.id.edtext_EndDay);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    public void back(View view) {
        this.finish();
    }

    public void openCalendar(View view) {
        switch (view.getId()) {
            case R.id.edtext_StartDay: {
                showCalendarDialog(startDay);
                break;
            }
            case R.id.edtext_EndDay:{
                showCalendarDialog(endDay);
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                text.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showFilterActivity(View view) {
        Intent intent = new Intent(this, LichSuGiaoDichActivity.class);
        startActivity(intent);
    }
}
package com.example.cinemaapp.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityDangKy extends AppCompatActivity {
    private Spinner spinner;
    private EditText starDay;
    private EditText editHoten, editSdt, editEmail, editMatkhau, editNgaysinh;
    private Button btnDangky;
    private RadioButton rabtnNam, rabtnNu;
    private Context context;






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
        context = this;

        //ngày sinh
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);

        starDay = (EditText) findViewById(R.id.editTextNgaySinh);
        //mũi tên quay lại
        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
        //actionBar.hide(); ẩn tên app
        //actionBar.setTitle("Đăng ký");//đặt tên app
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên


        final DBManager dbManager = new DBManager(this);
        editHoten = (EditText) findViewById(R.id.editTextHoTen);
        editSdt = (EditText)findViewById(R.id.editTextSDT);
        editEmail = (EditText)findViewById(R.id.editTextTNhapEmail);
        editMatkhau = (EditText)findViewById(R.id.editTextNhapPassword);
        rabtnNam =(RadioButton)findViewById(R.id.radioBtnNam);
        rabtnNu =(RadioButton)findViewById(R.id.radioBtnNu);
        editNgaysinh=(EditText)findViewById(R.id.editTextNgaySinh);
        btnDangky=(Button)findViewById(R.id.btnDangKy);
        spinner = (Spinner)findViewById(R.id.spinner);
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVien thanhVien= dkThanhVien();
                System.out.println(thanhVien);
                if(thanhVien!=null)
                {
                    if(dbManager.themThanhVien(thanhVien)){
                        ThongBaoThanhCong();
                    }else{
                        ThongBaoLoi();
                    }
                }

            }
        });
    }

    public void ThongBaoThanhCong(){
        Toast.makeText(this, "Them Thanh cong!", Toast.LENGTH_LONG).show();
    }

    public void ThongBaoLoi(){
        Toast.makeText(this, "Loi!", Toast.LENGTH_LONG).show();
    }

    private ThanhVien dkThanhVien(){
        String hoten= editHoten.getText().toString();
        String sdt= editSdt.getText().toString();
        String email= editEmail.getText().toString();
        String matkhau= editMatkhau.getText().toString();
        String gioitinh = rabtnNam.isChecked() ? getString( R.string.Nam) : getString(R.string.Nu);
        String ngaysinh= editNgaysinh.getText().toString();

        String diachi=  spinner.getSelectedItem().toString();

        ThanhVien thanhVien= new ThanhVien(hoten, sdt,email,matkhau,gioitinh,ngaysinh,diachi);
        return thanhVien;




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
            case R.id.editTextNgaySinh: {
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
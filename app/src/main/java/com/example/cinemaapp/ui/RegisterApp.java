package com.example.cinemaapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.example.cinemaapp.api.APIThemThanhVien;
import com.example.cinemaapp.model.Users;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class RegisterApp extends AppCompatActivity
{
    private EditText edtDate,editHoten,editEmail,editPassoword,editSDT,editDiaChi;
    private Button btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        editHoten=(EditText)findViewById(R.id.editTextHoTen);
        editSDT=(EditText)findViewById(R.id.editTextSDT);
        editEmail=(EditText)findViewById(R.id.editTextTNhapEmail);
        editPassoword=(EditText)findViewById(R.id.editTextNhapPassword);
        editDiaChi=(EditText)findViewById(R.id.editTextDiaChi) ;
        edtDate=(EditText)findViewById(R.id.editTextNgaySinh);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });
        btnDangKy=(Button)findViewById(R.id.btnDangKythanhvien);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    themthanhvien();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    private void chonngay()
    {
        Calendar calendar=Calendar.getInstance();
        int ngay=calendar.get(Calendar.DATE);
        int thang=calendar.get(Calendar.MONTH);
        int nam=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    public void themthanhvien() throws JSONException, ExecutionException, InterruptedException {
        if(editHoten.length()==0)
        {
            editHoten.setError("Không được bỏ trống!");
            return;
        }
        else if(editSDT.length()==0)
        {
            editSDT.setError("Không được bỏ trống!");
            return;
        }
        else if(editEmail.length()==0)
        {
            editEmail.setError("Không được bỏ trống!");
            return;
        }
        else if(editPassoword.length()==0)
        {
            editPassoword.setError("Không được bỏ trống!");
            return;
        }
        else if(edtDate.length()==0)
        {
            edtDate.setError("Không được bỏ trống!");
            return;
        }
        else if(editDiaChi.length()==0)
        {
            editDiaChi.setError("Không được bỏ trống!");
            return;
        }
        String hoten = ((EditText) findViewById(R.id.editTextHoTen)).getText().toString();
        String ngaysinh = ((EditText) findViewById(R.id.editTextNgaySinh)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextTNhapEmail)).getText().toString();
        String pass = ((EditText) findViewById(R.id.editTextNhapPassword)).getText().toString();
        String sdt = ((EditText) findViewById(R.id.editTextSDT)).getText().toString();
        String diachi = ((EditText) findViewById(R.id.editTextDiaChi)).getText().toString();
//        if(checkNull(hoten) || checkNull(pass)||checkNull(email)||checkNull(ngaysinh)||checkNull(sdt)||checkNull(diachi)){
////            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
//
//            return;
//        }
        Users users = new Users(email, pass,hoten,ngaysinh,diachi,sdt);
//        Toast.makeText(this, newWord.toString(), Toast.LENGTH_SHORT).show();
        String s= new APIThemThanhVien(this).execute(users).get();
        JSONObject jsonObject=new JSONObject(s);

        if(jsonObject.getString("mess").equals("true"))
        {

            Intent intent= new Intent(RegisterApp.this,LoginApp.class);

            Toast.makeText(this,"Đăng ký thành công!",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        //Toast.makeText(this,result,Toast.LENGTH_LONG).show();
    }

    private boolean checkNull(String str){
        if(str.isEmpty() || str.trim().isEmpty())
            return true;
        return false;
    }

    public void clickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

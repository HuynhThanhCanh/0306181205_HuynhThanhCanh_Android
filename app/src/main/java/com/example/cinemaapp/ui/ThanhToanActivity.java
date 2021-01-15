package com.example.cinemaapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.example.cinemaapp.api.APIGetting;
import com.example.cinemaapp.model.Ghe;
import com.example.cinemaapp.model.Lichchieu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ThanhToanActivity extends AppCompatActivity {

    Integer tongGiaTien, tongSoGhe;


        public Lichchieu LICH_CHIEU=new Lichchieu();
        public LinkedList<Ghe> Ghes = new LinkedList<Ghe>();
        String MaDSVe = "10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
//        ActionBar actionBar = getSupportActionBar(); //gọi để lấy đối tượng action bar
//        actionBar.hide(); //ẩn tên app
//        actionBar.setTitle("Thanh toán");//đặt tên app
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//dấu mũi tên

////      Bat dau code cua trang ne nha
//        Intent intent = getIntent();
//        tongGiaTien = intent.getIntExtra("tongtien", 0);
//        tongSoGhe = intent.getIntExtra("soghe", 0);
//
//        TextView txtSo = (TextView) findViewById(R.id.txtso);
//        TextView txtTong = (TextView) findViewById(R.id.txtTTTongTien);
//
//        NumberFormat format = new DecimalFormat("#,###");
//        String finalTongTien = format.format(tongGiaTien);
//
//        txtSo.setText(tongSoGhe.toString());
//        txtTong.setText(finalTongTien.toString() + "VNĐ");
////        Ket thuc phan cua Trang

        try {
            ReadJSONGhes();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ReadJSONLichChieu();
        } catch (JSONException e) {
            e.printStackTrace();
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
    public void ReadJSONLichChieu() throws JSONException {
        Intent intent = getIntent();

        String LichchieuText = intent.getStringExtra("LichChieu");

            JSONObject lc = new JSONObject(LichchieuText);
        String ID =lc.getString("MaLichChieu");
         LICH_CHIEU.MaLichChieu=ID;



    }
    public boolean checkVe() throws ExecutionException, InterruptedException {
        boolean kq = false;
        String mess="Ghế đã có người đặt :";
        int len = Ghes.size();
            for (int i=0;i<len;i++){
                    String s = new APIGetting(this).execute("check-ghe?maLichChieu="+LICH_CHIEU.MaLichChieu+"&maGhe="+Ghes.get(i).MaGhe).get();

            if (s.equals("true"))
            {
                mess+=Ghes.get(i).MaGhe+", ";
                kq = true;
            }
        }

        if (kq ==true)
        {
            Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
        }

        return kq;
    }
    public void ReadJSONGhes() throws JSONException {
        Intent intent = getIntent();
        String GhesText = intent.getStringExtra("Ghes");
        JSONArray GHES = new JSONArray(GhesText);
        int len  = GHES.length();
        for (int  i =0;i<len;i++)
        {
            JSONObject ghe =GHES.getJSONObject(i);
            Ghe ghe1 =new Ghe();
            ghe1.MaGhe=ghe.getString("MaGhe");
            ghe1.status=Integer.parseInt(ghe.getString("status"));

            Ghes.addLast(ghe1);
        }
    }

    public void ThanhToan(View view) {
        try {
            if (!checkVe())
            {

                int tien =Ghes.size()*50000;
//                Toast.makeText(this,"Ma danh sach ve la  : "+MaDSVe,Toast.LENGTH_LONG).show();
                String s = new APIGetting(this).execute("themdanhsachve?SoLuong="+Ghes.size()+"&TongThanhTien="+tien+"&MaTV="+MainActivity.MaThanhVien).get();

                try {
                    JSONObject DanhSachVe = new JSONObject(s);
                    MaDSVe=DanhSachVe.getString("MaDsVe");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                int len = Ghes.size();
                for (int i=0;i<len;i++){
                    String ss = new APIGetting(this).
                            execute("themve?MaDsVe="+MaDSVe
                            +"&MaGhe="+Ghes.get(i).MaGhe
                                    +"&ThanhTien=50000"
                                    +"&MaLichChieu="+LICH_CHIEU.MaLichChieu).get();



                }
                Toast.makeText(this,"Đặt vé thành công !",Toast.LENGTH_LONG).show();

                Intent intent= new Intent(this,MovieListActivity.class);
                startActivity(intent);
                this.finish();

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
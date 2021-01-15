package com.example.cinemaapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.example.cinemaapp.R;
import com.example.cinemaapp.api.APIGetting;
import com.example.cinemaapp.model.Ghe;
import com.example.cinemaapp.model.Lichchieu;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class ThanhToanActivity extends AppCompatActivity {

    Integer tongGiaTien, tongSoGhe;


        public Lichchieu LICH_CHIEU=new Lichchieu();
        public LinkedList<Ghe> Ghes = new LinkedList<Ghe>();
        String MaDSVe = "1";

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


        initView();

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
        LICH_CHIEU.TenPhim=   lc.getString("TenPhim");
        LICH_CHIEU.MaPhim=    lc.getString("MaPhim");
        LICH_CHIEU.MaRap=     lc.getString("MaRap");
        LICH_CHIEU.SuatChieu= lc.getString("ThoiGianChieu");
        LICH_CHIEU.NgayChieu= lc.getString("NgayChieu");






    }

    public void initView()
    {

        TextView txtTenPhim = (TextView) findViewById(R.id.txtTenPhim);
        TextView txtNgayChieu = (TextView) findViewById(R.id.txtThoiGianChieu);
        TextView txtTenRap = (TextView) findViewById(R.id.txtTenRap);
        TextView txtGhe = (TextView) findViewById(R.id.txtSoGhe);
        TextView txtNhan = (TextView) findViewById(R.id.txtNhan);
        TextView txtTongTien = (TextView) findViewById(R.id.txtTTTongTien);
        TextView txtSoLuong = (TextView) findViewById(R.id.txtso);

        txtTenPhim.setText(ThongTinPhimActivity.MOVIE.getTitle());
        txtNgayChieu.setText(SoDoRapActivity.LICH_CHIEU.NgayChieu + " : "+SoDoRapActivity.LICH_CHIEU.SuatChieu);
        txtTenRap.setText("Rap : "+SoDoRapActivity.LICH_CHIEU.MaRap);
        txtNhan.setText(ThongTinPhimActivity.MOVIE.getLabel());

        ImageView imageView = (ImageView) findViewById(R.id.imageMovie);
        Glide.with(this).load(ThongTinPhimActivity.MOVIE.getCoverPhoto()).into(imageView);
        String ghess="";
                for (int i = 0;i<Ghes.size();i++)
                {
                    ghess+= (Ghes.get(i).MaGhe).substring(5)+", ";
                }
        txtGhe.setText(ghess);
                Double tongtien = 0.0;
        for (int i=0;i<Ghes.size();i++){

            tongtien+=Ghes.get(i).Gia;

        }

        txtTongTien.setText(tongtien+".");
        int len  = Ghes.size();
       txtSoLuong.setText(len+".");



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
            ghe1.Gia=ghe.getDouble("Gia");
            ghe1.status=Integer.parseInt(ghe.getString("status"));

            Ghes.addLast(ghe1);
        }
    }

    public void ThanhToan(View view) {
        try {
            if (!checkVe())
            {
                Double tien =0.0;
                for (int i =0;i<Ghes.size();i++)
                {
                    tien+=Ghes.get(i).Gia;
                }

//                Toast.makeText(this,"Ma danh sach ve la  : "+MaDSVe,Toast.LENGTH_LONG).show();
                String s = new APIGetting(this).execute("themdanhsachve?SoLuong="+Ghes.size()+"&TongThanhTien="+tien+"&MaTV="+MainActivity.MaThanhVien).get();

                try {
                    JSONObject DanhSachVeText = new JSONObject(s);
                    String DanhSachVe=DanhSachVeText.getString("message");
                    JSONObject DSV = new JSONObject(DanhSachVe);
                    MaDSVe=DSV.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                int len = Ghes.size();
                for (int i=0;i<len;i++){

                    String ss = new APIGetting(this).
                            execute("themve?MaDsVe="+MaDSVe
                            +"&MaGhe="+Ghes.get(i).MaGhe
                                    +"&ThanhTien="+Ghes.get(i).Gia
                                            +"&MaLichChieu="+LICH_CHIEU.MaLichChieu).get();



                }
                Toast.makeText(this,"Đặt vé thành công !",Toast.LENGTH_LONG).show();

                Intent intent= new Intent(this,MovieListActivity.class);

                this.finish();
                startActivity(intent);


            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
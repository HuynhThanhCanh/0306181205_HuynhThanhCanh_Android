package com.example.cinemaapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
//import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.cinemaapp.adapter.Adapter;
import com.example.cinemaapp.adapter.AdapterSlider;
import com.example.cinemaapp.model.Model;
import com.example.cinemaapp.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    private int stt =0;
    ViewPager viewPager2;

    ViewPager2 viewPagerVP;
    Adapter adapter;
    private Handler sliderHandler = new Handler();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<Model> models;
    List<Model> models2;
    Button btn_dangchieu;
    final List<Model> dangchieu_ats =new ArrayList<>();
    Button btn_sapchieu;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //test viewpager 2


        constructormodels2();
        viewPagerVP=(ViewPager2)findViewById(R.id.viewPagerImageSlider);
        // khởi tao các đối tượng  add vào silder đang chiếu
        dangchieu_ats.add(new Model(R.drawable.matbet,"Mắt Biếc","Tình cảm"));
        dangchieu_ats.add(new Model(R.drawable.rom,"Ròm","Hành động"));
        dangchieu_ats.add(new Model(R.drawable.trangmau,"Tiệc Trăng Máu","Hài Hước"));
        dangchieu_ats.add(new Model(R.drawable.venom,"Venom","Kinh dị"));
        dangchieu_ats.add(new Model(R.drawable.trangquynh,"Trạng Quỳnh","Tâm lý"));

        //end test

        initslider1(dangchieu_ats);


        btn_dangchieu = (Button) findViewById(R.id.btn_dang_chieu);
        btn_dangchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initslider1(dangchieu_ats);
            }
        });

        btn_sapchieu = (Button) findViewById(R.id.btn_sap_chieu);
        btn_sapchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    initslider1(models);
            }
        });

//         toolbar = findViewById(R.id.toolbar);
//         setSupportActionBar(toolbar);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


       navigationView = findViewById(R.id.nav_view);
//       View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               UserMenuSelected(item);
               return false;
           }
       });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    // xử lí khi người dùng click vào menu ở đây

    public void UserMenuSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.theoPhim :
            {
                Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.dangnhap_dangky :
            {
                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent);
                break;
            }
        }


    }

    public  void constructormodels2()
    {
        // khởi tạo các đối tượng add vào silder sắp chiếu
        models = new ArrayList<>();
        models.add(new Model(R.drawable.img_1,"Tiec Trang Mau","Hai huoc"));
        models.add(new Model(R.drawable.img_2,"365 ngay yeu anh","tinh cam"));
        models.add(new Model(R.drawable.img_3,"minions","hoat hinh"));
        models.add(new Model(R.drawable.img_2,"EngGame","Hanh Dong"));
        models.add(new Model(R.drawable.img_3,"Em chua 18","Tam ly"));
    }


    public void xem_chi_tiet(View view) {
        String tb = "Ban da chon phim"+Integer.toString(stt);
        Toast.makeText(MainActivity.this,tb,Toast.LENGTH_LONG).show();
    }
    public  void initslider1(List<Model> dangchieus)
    {


        viewPagerVP.setAdapter(new AdapterSlider(dangchieus,viewPagerVP));

        viewPagerVP.setClipToPadding(false);
        viewPagerVP.setClipChildren(false);
        viewPagerVP.setOffscreenPageLimit(3);
        viewPagerVP.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.87f + r * 0.15f);

            }
        });
        viewPagerVP.setPageTransformer(compositePageTransformer);
    }

    public void showFilmInformations(View view) {
        Intent intent = new Intent(this, ThongTinPhimActivity.class);
        startActivity(intent);
    }

    public void showTrangCaNhan(View view) {
        Intent intent = new Intent(this, TrangCaNhanActivity.class);
        startActivity(intent);
    }
}
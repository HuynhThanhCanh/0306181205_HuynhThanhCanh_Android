package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.tabs.TabItem;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //test viewpager 2


        constructormodels2();
        viewPagerVP=(ViewPager2)findViewById(R.id.viewPagerImageSlider);


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
//        TabItem tab1= (TabItem) findViewById(R.id.tabDangChieu);
//        TabItem tab2 = (TabItem) findViewById(R.id.tabSapChieu);
//        tab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initslider1(dangchieu_ats);
//            }
//        });
//        tab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                initslider1(models);
//            }
//        });

//                initslide(models,viewPager);
//                initslide(models2,viewPager2);
    }
    public  void constructormodels2()
    {
        models = new ArrayList<>();
        models.add(new Model(R.drawable.img_1,"Tiec Trang Mau","Hai huoc"));
        models.add(new Model(R.drawable.img_2,"365 ngay yeu anh","tinh cam"));
        models.add(new Model(R.drawable.img_3,"minions","hoat hinh"));
        models.add(new Model(R.drawable.img_2,"EngGame","Hanh Dong"));
        models.add(new Model(R.drawable.img_3,"Em chua 18","Tam ly"));
    }

//    public void initslide(List<Model> dsModel,ViewPager slideViewPager)
//    {
//        adapter = new Adapter(dsModel,this);
//
//        slideViewPager.setAdapter(adapter);
//        slideViewPager.setClipChildren(false);
//        slideViewPager.setOffscreenPageLimit(3);
//        slideViewPager.setPadding(100,0,100,0);
//        Integer[] colors_temp ={getResources().getColor(R.color.color1),
//                getResources().getColor(R.color.color2),
//                getResources().getColor(R.color.color3),
//                getResources().getColor(R.color.color4)};
//
//        colors = colors_temp;
//
//        slideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                if (position<(adapter.getCount()-1) &&position <(colors.length-1))
////                {
////                    viewPager.setBackgroundColor(
////
////                            (Integer) argbEvaluator
////                                    .evaluate(positionOffset
////                                            ,colors[position]
////                                            ,colors[position+1])
////                    );
////
////                }
////                else
////                {
////                    viewPager.setBackgroundColor(colors[colors.length-1]);
////                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                stt=position+1;
//                sliderHandler.removeCallbacks(sliderRunnable2);
//                sliderHandler.postDelayed(sliderRunnable2,3000);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }

//    private Runnable sliderRunnable2 = new Runnable() {
//        @Override
//        public void run() {
//            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
//        }
//    };

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
    public void thongbao( View view)
    {

        Toast.makeText(this,Integer.toString(stt),Toast.LENGTH_LONG).show();
    }


}
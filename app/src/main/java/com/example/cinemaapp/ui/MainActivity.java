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
import com.example.cinemaapp.api.MoiveAsync;
import com.example.cinemaapp.model.Model;
import com.example.cinemaapp.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    private int stt =0;
    ViewPager viewPager2;
    private String URLImage="http://192.168.1.2:8000/image/phim/";
    ViewPager2 viewPagerVP;
    Adapter adapter;
    private Handler sliderHandler = new Handler();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    LinkedList<Model> MoviesDC= new LinkedList<Model>();
    LinkedList<Model> MoviesSC= new LinkedList<Model>();
    Button btn_dangchieu;

    Button btn_sapchieu;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            LoadMovies("http://192.168.1.2:8000/api/phim",MoviesDC,URLImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //test viewpager 2

        viewPagerVP=(ViewPager2)findViewById(R.id.viewPagerImageSlider);


        initslider1(MoviesDC);


        btn_dangchieu = (Button) findViewById(R.id.btn_dang_chieu);
        btn_dangchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//               initslider1();
                selectedTab(view);
            }
        });

        btn_sapchieu = (Button) findViewById(R.id.btn_sap_chieu);
        btn_sapchieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    initslider1(MoviesDC);
                selectedTab(view);
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
       View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               UserMenuSelected(item);
               return false;
           }
       });


    }
    public void selectedTab(View view){
        Button btn1 = (Button)findViewById(R.id.after1);
        Button btn2 = (Button)findViewById(R.id.after2);
        btn1.setBackgroundResource(R.color.tabSelected);
        switch (view.getId()){
            case R.id.btn_dang_chieu:
            {
                btn1.setBackgroundResource(R.color.tabSelected);

                btn2.setBackgroundResource(R.color.tabNonSelected);

            }break;
            case R.id.btn_sap_chieu:
            {
                btn2.setBackgroundResource(R.color.tabSelected);
                btn1.setBackgroundResource(R.color.tabNonSelected);


            }break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
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
//            case R.id.dangnhap_dangky :
//            {
//                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
//                startActivity(intent);
//                break;
//            }
        }


    }



    public void xem_chi_tiet(View view) {
        String tb = "Ban da chon phim"+Integer.toString(stt);
        Toast.makeText(MainActivity.this,tb,Toast.LENGTH_LONG).show();
    }
    public void LoadMovies(String urlAPI, LinkedList movies,String URLimage) throws ExecutionException, InterruptedException, JSONException {
        String jsonText = new MoiveAsync().execute(urlAPI).get();
        JSONArray jsonArray = new JSONArray(jsonText);
        int len=jsonArray.length();
        for (int i =0;i<len;i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String image =jsonObject.getString("HinhAnh");
            String name =jsonObject.getString("TenPhim");
            String title=jsonObject.getString("DaoDien");
            Model movie = new Model(URLimage+image,name,title);
            movies.addLast(movie);
        }



    }
    public  void initslider1(List<Model> dangchieus)
    {

        AdapterSlider adapterSlider =new AdapterSlider(dangchieus,viewPagerVP);
        adapterSlider.setContext(getApplicationContext());
        viewPagerVP.setAdapter(adapterSlider);

        viewPagerVP.setClipToPadding(false);
        viewPagerVP.setClipChildren(false);
        viewPagerVP.setOffscreenPageLimit(3);
        viewPagerVP.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.87f + r * 0.15f);

            }
        });
        viewPagerVP.setPageTransformer(compositePageTransformer);
        viewPagerVP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks((Runnable) silderRunnable);
                sliderHandler.postDelayed((Runnable) silderRunnable,4000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }
    private Runnable silderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPagerVP.setCurrentItem(viewPagerVP.getCurrentItem()+1);
        }
    };

    public void showFilmInformations(View view) {
        Intent intent = new Intent(this, GiaodienActivity.class);
        startActivity(intent);
    }

    public void showTrangCaNhan(View view) {
        Intent intent = new Intent(this, TrangCaNhanActivity.class);
        startActivity(intent);
    }
}
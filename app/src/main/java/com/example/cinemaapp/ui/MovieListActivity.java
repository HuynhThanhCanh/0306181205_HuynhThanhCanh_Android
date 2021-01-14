package com.example.cinemaapp.ui;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.api.APIGetting;
import com.example.cinemaapp.api.APISearchPhim;
import com.example.cinemaapp.model.MovieItemClickListener;
import com.example.cinemaapp.adapter.SlidePagerAdapter;
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.MovieSapChieu;
import com.example.cinemaapp.model.Slide;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class MovieListActivity extends AppCompatActivity implements MovieItemClickListener {
    //Đầu tiên khai báo RecyclerView
private List<Slide>lstslides;
private ViewPager slidepager;
private TabLayout indicator;
private RecyclerView MoviesRV;
private RecyclerView MoviesRV1;
private EditText searchInput;
private String URLimage="http://192.168.5.24:8080/image/phim/";
private  String jsonString;
public LinkedList<Movie> lst_movie=new LinkedList<>() ;
public LinkedList<Movie> lst_movie2= new LinkedList<>() ;
//static LinkedList<Movie> lst_movie2= new LinkedList<>() ;
    MovieAdapter Adapter;
    MovieAdapter Adapter2;
//MovieSapChieuAdapter Adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        slidepager=findViewById(R.id.slider_pager);
        indicator =findViewById(R.id.indicator);
        //recyclerview đang chiếu
        MoviesRV=findViewById(R.id.Rv_movies);
        //recyclerview sắp chiếu
        MoviesRV1=findViewById(R.id.Rv_movies1);
        //input search
        searchInput=findViewById(R.id.inputSearch);
        searchInput.setSelected(false);

        lstslides= new ArrayList<>();
        lstslides.add(new Slide(R.drawable.mulan,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.spidercover,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.anvanger,"slide Title /nmore text here"));
        lstslides.add(new Slide(R.drawable.rom,"slide Title /nmore text here"));
        SlidePagerAdapter adapter = new SlidePagerAdapter(this,lstslides);
        slidepager.setAdapter(adapter);
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MovieListActivity.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(slidepager,true);

        try {

            ReadMoives("phimDangChieu",lst_movie,URLimage);
            Adapter= new MovieAdapter(lst_movie,this,this);
            MoviesRV.setAdapter(Adapter);
            MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {

            ReadMoives("phimSapChieu",lst_movie2,URLimage);
            Adapter2= new MovieAdapter(lst_movie2,this,this);
            MoviesRV1.setAdapter(Adapter2);
            MoviesRV1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        searhPhim(this);

//        //setup RecylerView Phim đang chiếu
//        List<Moive>lstMovies=new ArrayList<>();
//        lstMovies.add(new Moive("Moana",R.drawable.moana,R.drawable.moana,"4","12+"));
//        lstMovies.add(new Moive("Black P",R.drawable.blackp,R.drawable.blackp,"4","18+"));
//        lstMovies.add(new Moive("Mulan",R.drawable.mulan,R.drawable.mulan,"5","18+"));
//        lstMovies.add(new Moive("Avanger",R.drawable.anvanger,R.drawable.anvanger,"1","18+"));
//        lstMovies.add(new Moive("Ròm",R.drawable.rom,R.drawable.rom,"2","12+"));
//        lstMovies.add(new Moive("Tiệc trăng máu",R.drawable.tiectrangmau,R.drawable.tiectrangmau,"3","12+"));
//        MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies,this);
//        MoviesRV.setAdapter(movieAdapter);
//        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//
//        //setup RecylerView Phim Sắp chiếu
//        List<MovieSapChieu>lstMovies2=new ArrayList<>();
//        lstMovies2.add(new MovieSapChieu("Avanger",R.drawable.anvanger,R.drawable.anvanger,"1","18+"));
//        lstMovies2.add(new MovieSapChieu("Ròm",R.drawable.rom,R.drawable.rom,"2","12+"));
//        lstMovies2.add(new MovieSapChieu("Tiệc trăng máu",R.drawable.tiectrangmau,R.drawable.tiectrangmau,"3","12+"));
//        lstMovies2.add(new MovieSapChieu("Moana",R.drawable.moana,R.drawable.moana,"4","12+"));
//        lstMovies2.add(new MovieSapChieu("Black P",R.drawable.blackp,R.drawable.blackp,"4","18+"));
//        lstMovies2.add(new MovieSapChieu("Mulan",R.drawable.mulan,R.drawable.mulan,"5","18+"));
//
//
//        MovieSapChieuAdapter movieAdapter1 = new MovieSapChieuAdapter();
//        MoviesRV1.setAdapter(movieAdapter1);
//        MoviesRV1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

//        try {
//            jsonString = new APIGetting(this).execute("true").get();
//          if(get_list_movieDangChieu(jsonString,lst_movie)){
//
//             Adapter= new MovieAdapter(lst_movie,this,this);
//              MoviesRV.setAdapter(Adapter2);
//              MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//            }
//            else{
//                MoviesRV.setVisibility(View.INVISIBLE);
//
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } try {
//            jsonString = new APIGetting(this).execute().get();
//            if(get_list_movieDangChieu(jsonString,lst_movie2)){
//
//                Adapter2= new MovieSapChieuAdapter(lst_movie2,this,this);
//                MoviesRV1.setAdapter(Adapter);
//                MoviesRV1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//            }
//            else{
//                MoviesRV1.setVisibility(View.INVISIBLE);
//
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }



    }

public void searhPhim(Context context)
{
    searchInput.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Adapter.getFilter().filter(s);

            String getText = ((EditText) findViewById(R.id.inputSearch)).getText().toString();
            if(getText.isEmpty())
            {
                //MoviesRV
                MoviesRV=findViewById(R.id.Rv_movies);
                Adapter= new MovieAdapter(lst_movie,context, (MovieItemClickListener) context);
                MoviesRV.setAdapter(Adapter);
                MoviesRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            }
            LinkedList<Movie> result = new LinkedList<Movie>();
            int len = lst_movie.size();
            for (int i = 0; i < len; i++) {
                if (lst_movie.get(i).getTitle().toLowerCase().contains(getText.toLowerCase()))
                    result.addLast(lst_movie.get(i));
            }
//
            MoviesRV = findViewById(R.id.Rv_movies);
            Adapter = new MovieAdapter(result, context, (MovieItemClickListener) context);
            MoviesRV.setAdapter(Adapter);
            MoviesRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

//            String result= searchInput.getText().toString();
//            Movie movie= new Movie(result);
//            try {
//                jsonString= new APISearchPhim(context).execute().get();
//                JSONArray jsonArray = new JSONArray(jsonString);
//
//                int num = jsonArray.length();
//                for (int i = 0; i < num; i++)
//                {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//
//                    movie.setTitle(jsonObject.getString("TenPhim"));
//                    movie.setRating(jsonObject.getString("Diem"));
//                    String HinhAnh = jsonObject.getString("HinhAnh");
//                    movie.setGenre(jsonObject.getString("TenLoaiPhim"));
//                    movie.setLabel(jsonObject.getString("TenGioiHan"));
//                    movie.setDirectors(jsonObject.getString("DaoDien"));
//                    movie.setStreamingLink(jsonObject.getString("LinkPhim"));
//                    movie.setDescription(jsonObject.getString("NoiDung"));
//                    String link = URLimage + HinhAnh;
//                    movie.setCoverPhoto(link);
//                    movie.setThumbnail(link);
//                    list.add(movie);
//
//                }
//
//
//                    Adapter= new MovieAdapter(list,context, (MovieItemClickListener) context);
//                    MoviesRV.setAdapter(Adapter);
//                MoviesRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//
//
//                } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


        }

        @Override
        public void afterTextChanged(Editable s) {
            //Adapter2.getFilter().filter(s);

        }
    });
}
//    public Boolean get_list_movieDangChieu(String js,LinkedList<Movie> l){
//        l= new LinkedList<>();
//        String IP="http://192.168.137.15:8080/image/phim/";
//
//        try {
//            JSONArray jsonArray= new JSONArray(js);
//
//            int num= jsonArray.length();
//            for(int i=0; i<num; i++){
//                JSONObject jsonObject= jsonArray.getJSONObject(i);
//                Movie movie= new Movie();
//
//                movie.setTitle(jsonObject.getString("TenPhim"));
//                movie.setRating(jsonObject.getString("Diem"));
//                String HinhAnh=jsonObject.getString("HinhAnh");
//                String link=IP+HinhAnh;
//                movie.setCoverPhoto(link);
//                movie.setThumbnail(link);
//                l.add(movie);
//            }
//            return  true;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    //get phim sap chieu chieu
//    public Boolean get_list_movieSapChieu(String js){
//        lst_movie= new LinkedList<>();
//        String IP="http://192.168.137.15:8080/image/phim/";
//
//        try {
//            JSONArray jsonArray= new JSONArray(js);
//
//            int num= jsonArray.length();
//            for(int i=0; i<num; i++){
//                JSONObject jsonObject= jsonArray.getJSONObject(i);
//                Movie movie= new Movie();
//
//                movie.setTitle(jsonObject.getString("TenPhim"));
//                movie.setRating(jsonObject.getString("Diem"));
//                String HinhAnh=jsonObject.getString("HinhAnh");
//                String link=IP+HinhAnh;
//                movie.setCoverPhoto(link);
//                movie.setThumbnail(link);
//                lst_movie.add(movie);
//            }
//            return  true;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, ThongTinPhimActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("ID",movie.getMaPhim());
        intent.putExtra("title",movie.getTitle());
       intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
       intent.putExtra("rating",movie.getRating());
        intent.putExtra("genre",movie.getGenre());
       intent.putExtra("Directors",movie.getDirectors());
       intent.putExtra("trailer",movie.getStreamingLink());
       intent.putExtra("NoiDung",movie.getDescription());
       intent.putExtra("label",movie.getLabel());
        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MovieListActivity.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this,"Bạn vừa chọn Phim " + movie.getTitle(),Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieSapchieuClick(MovieSapChieu movie, ImageView movieImageView) {
        Intent intent = new Intent(this, ThongTinPhimActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());

        ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MovieListActivity.this,
                movieImageView,"sharedName");
        startActivity(intent,options.toBundle());

        Toast.makeText(this,"Bạn vừa chọn Phim " + movie.getTitle(),Toast.LENGTH_LONG).show();
    }


    class SliderTimer extends TimerTask{
        @Override
        public void run() {
            MovieListActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slidepager.getCurrentItem()<lstslides.size()-1)
                    {
                        slidepager.setCurrentItem(slidepager.getCurrentItem()+1);
                    }
                    else

                        slidepager.setCurrentItem(0);
                }
            });
        }
    }
    public void ReadMoives(String s,LinkedList<Movie> list,String URLimage) throws ExecutionException, InterruptedException, JSONException {
        String jsonString = new APIGetting(this).execute(s).get();

        JSONArray jsonArray = new JSONArray(jsonString);

        int num = jsonArray.length();
        for (int i = 0; i < num; i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Movie movie = new Movie();
            movie.setMaPhim(jsonObject.getString("MaPhim"));
            movie.setTitle(jsonObject.getString("TenPhim"));
            movie.setRating(jsonObject.getString("Diem"));
            String HinhAnh = jsonObject.getString("HinhAnh");
            movie.setGenre(jsonObject.getString("TenLoaiPhim"));
            movie.setLabel(jsonObject.getString("TenGioiHan"));
            movie.setDirectors(jsonObject.getString("DaoDien"));
            movie.setStreamingLink(jsonObject.getString("LinkPhim"));
            movie.setDescription(jsonObject.getString("NoiDung"));
            String link = URLimage + HinhAnh;
            movie.setCoverPhoto(link);
            movie.setThumbnail(link);
            list.add(movie);

        }
    }
}
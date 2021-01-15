package com.example.cinemaapp.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.api.APIGetting;
import com.example.cinemaapp.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ThongTinPhimActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String MaPhim="1";
    private String Host=MainActivity.HostDomain;
    private ImageView MovieThumbnaiImg,MovieCoverImg;

    public static Drawable thisImg;
    private TextView tv_title,tv_description,sao,genrename,daoDien,noiDung,doTuoi;
     private FloatingActionButton play_fab;
    private Movie movie = new Movie();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phim);
        XuLyMuave();
//        thisImg=MovieThumbnaiImg.getDrawable();
        MaPhim=getIntent().getExtras().getString("ID");
    //    Toast.makeText(getApplicationContext(),"Ma P him : "+MaPhim,Toast.LENGTH_LONG).show();
        inViews();
        Toast.makeText(this,"Ma thanh vien hien tai : "+MainActivity.MaThanhVien,Toast.LENGTH_LONG).show();
    }

     void XuLyMuave() {
         findViewById(R.id.btndatve).setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), GiaodienActivity.class);
                 intent.putExtra("ID",MaPhim);
                 startActivity(intent);
             }
         });
    }

    private  void inViews()
    {
        try {
            getThongTinPhim(MaPhim);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //play_fab=findViewById(R.id.play_fab);
//        String movieTitle =getIntent().getExtras().getString("title");
//        String imageResoureId=getIntent().getExtras().getString("imgURL");
//        String imageCover=getIntent().getExtras().getString("imgCover");
//        String rating=getIntent().getExtras().getString("rating");
//        String genre=getIntent().getExtras().getString("genre");
//        String Directors=getIntent().getExtras().getString("Directors");
//        String description=getIntent().getExtras().getString("NoiDung");
//        String tuoi=getIntent().getExtras().getString("label");

        String movieTitle =movie.getTitle();
        String imageResoureId=movie.getThumbnail();
        String     imageCover=movie.getCoverPhoto();
        String         rating=movie.getRating();
        String          genre=movie.getGenre();
        String      Directors=movie.getDirectors();
        String    description=movie.getDescription();
        String           tuoi=movie.getLabel();

        MovieThumbnaiImg=findViewById(R.id.detail_movie_img);
      Glide.with(this).load(imageResoureId).into(MovieThumbnaiImg);
       MovieCoverImg=findViewById(R.id.detail_movie_cover);
       // Picasso.get().load(movie.getCoverPhoto()).into( MovieThumbnaiImg);
        Glide.with(this).load(imageCover).into(MovieCoverImg);
       tv_title=findViewById(R.id.detail_movie_title);
       tv_title.setText(movieTitle);

       sao=findViewById(R.id.so_sao);
       sao.setText(rating);
        genrename=findViewById(R.id.ten_the_loai);
        genrename.setText(genre);
        daoDien=findViewById(R.id.ten_dao_dien);
        daoDien.setText(Directors);
        noiDung=findViewById(R.id.ndphim1);
        noiDung.setText(description);
        doTuoi=findViewById(R.id.tuoi1);
        doTuoi.setText(tuoi);
       MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        tv_title.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));

        movie.setCoverPhoto(imageCover);
    }

    public void play_trailer(View view) {
//        String link =getIntent().getExtras().getString("trailer");
        String link=movie.getStreamingLink();
        String movieTitle =getIntent().getExtras().getString("title");
        play_fab=findViewById(R.id.play_fab);
        Intent intent = new Intent(this, TrailerActivity.class);
        intent.putExtra("trailer",link);
        startActivity(intent);
        Toast.makeText(this,"Bạn chọn xem  Phim " + movieTitle,Toast.LENGTH_LONG).show();
    }
    
    public void getThongTinPhim(String MaPhim) throws ExecutionException, InterruptedException, JSONException {
        String s= new APIGetting(this).execute("phim/"+MaPhim).get();
        JSONArray jsonArray = new JSONArray(s);

        if (s!=null) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            movie.setMaPhim(jsonObject.getString("MaPhim"));
            movie.setTitle(jsonObject.getString("TenPhim"));
            movie.setRating(jsonObject.getString("Diem"));

            movie.setGenre(jsonObject.getString("TenLoaiPhim"));
            movie.setLabel(jsonObject.getString("TenGioiHan"));
            movie.setDirectors(jsonObject.getString("DaoDien"));
            movie.setStreamingLink(jsonObject.getString("LinkPhim"));
            movie.setDescription(jsonObject.getString("NoiDung"));
            movie.setCoverPhoto(Host + "image/phim/" +  jsonObject.getString("HinhAnh"));
            movie.setThumbnail(Host + "image/phim/" +  jsonObject.getString("HinhAnh"));

        }
    }

}
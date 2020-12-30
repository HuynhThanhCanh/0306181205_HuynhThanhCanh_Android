package com.example.cinemaapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Model;
import com.example.cinemaapp.ui.GiaodienActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public  class AdapterSlider extends RecyclerView.Adapter<AdapterSlider.SliderViewHolder> {
    private List<Model> dangchieu_ats;
    private ViewPager2 viewPager2;
    private LayoutInflater layoutInflater;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;


    public AdapterSlider(List<Model> dangchieu_ats, ViewPager2 viewPager2) {
        this.dangchieu_ats = dangchieu_ats;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.silder_view_pager2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(dangchieu_ats.get(position));
        holder.setHeader(dangchieu_ats.get(position));
        holder.setTxtCategory(dangchieu_ats.get(position));
        if (position==dangchieu_ats.size()-2)
        {

            viewPager2.post(runnable);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GiaodienActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dangchieu_ats.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder
    {
        private RoundedImageView imageView;
        private  TextView txtHeader;
        private TextView txtCategory;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlide);
            txtHeader=itemView.findViewById(R.id.txtHeader);
            txtCategory=itemView.findViewById(R.id.txtCategory);

        }
        void setImage(Model dangchieu_at)
        {

//            imageView.setImageResource(dangchieu_at.getImage());
            LoadImmage loadImmage = new LoadImmage(imageView);
            loadImmage.execute(dangchieu_at.getImageURL());
        }
        void setHeader(Model dangchieu_at)
        {
            txtHeader.setText(dangchieu_at.getTitle());
        }
        void setTxtCategory(Model dangchieu_at)
        {
            txtCategory.setText(dangchieu_at.getDescription());
        }
        public class LoadImmage extends AsyncTask<String,Void, Bitmap>
        {
            ImageView imageView;

            public LoadImmage(ImageView imageView) {
                this.imageView = imageView;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String url = strings[0];
                Bitmap bitmap =null;

                try {
                    InputStream inputStream = new java.net.URL(url).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return bitmap;
            }
        }

    }
//    Context context;
//    LayoutInflater layoutInflater;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dangchieu_ats.addAll(dangchieu_ats);
            notifyDataSetChanged();
        }
    };

    public int getCount() {
        return dangchieu_ats.size();
    }
    //    public Adapterdangchieu(Context context)
//    {
//        this.C=context;
//    }
//    public int[] slide_imgs={
//            R.drawable.venom,R.drawable.matbet,R.drawable.rom,R.drawable.trangmau,R.drawable.trangquynh
//    };
//    public String[] names={
//            "VENOM", "MẮT BIẾC","RÒM","TIỆC TRĂNG MÁU","TRẠNG QUỲNH"
//    };
//    public String[] times={
//            "1giờ 30phút 30 Thg 10 2020","1giờ 30phút 30 Thg 10 2020","1giờ 30phút 30 Thg 10 2020","1giờ 30phút 30 Thg 10 2020","1giờ 30phút 30 Thg 10 2020"
//    };
//
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view==(RelativeLayout) object;
//    }

    @NonNull

//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view=layoutInflater.inflate(R.layout.silder_view_pager2,container,false);
//
//        ImageView slide_img=(ImageView)view.findViewById(R.id.imageSlide);
//        TextView txt_name=(TextView)view.findViewById(R.id.txtHeader);
//        TextView txt_time=(TextView)view.findViewById(R.id.txtCategory);
//
//        slide_img.setImageResource(image[position]);
//        txt_name.setText(names[position]);
//        txt_time.setText(times[position]);
//        container.addView(view);
//        return  view;
//    }


    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

}


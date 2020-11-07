package com.example.cinemaapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public  class AdapterSlider extends RecyclerView.Adapter<AdapterSlider.SliderViewHolder> {
    private List<Model> dangchieu_ats;
    private ViewPager2 viewPager2;
    private LayoutInflater layoutInflater;
    private Context context;


    AdapterSlider(List<Model> dangchieu_ats, ViewPager2 viewPager2) {
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
            imageView.setImageResource(dangchieu_at.getImage());
        }
        void setHeader(Model dangchieu_at)
        {
            txtHeader.setText(dangchieu_at.getTitle());
        }
        void setTxtCategory(Model dangchieu_at)
        {
            txtCategory.setText(dangchieu_at.getDescription());
        }
    }
//    Context context;
//    LayoutInflater layoutInflater;



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


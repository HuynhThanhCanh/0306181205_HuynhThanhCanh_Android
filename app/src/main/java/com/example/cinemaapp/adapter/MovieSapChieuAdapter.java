package com.example.cinemaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.MovieItemClickListener;
import com.example.cinemaapp.model.MovieSapChieu;

import java.util.ArrayList;
import java.util.List;

public class MovieSapChieuAdapter  extends RecyclerView.Adapter<MovieSapChieuAdapter.MyViewHolder> implements Filterable {
    Context context;
    List<MovieSapChieu> mData;
    List<MovieSapChieu> mDatafilter;
    MovieItemClickListener movieItemClickListener;


    public MovieSapChieuAdapter(Context context, List<MovieSapChieu> mdata, MovieItemClickListener listener) {
        this.context = context;
        this.mData = mdata;
        this.movieItemClickListener = listener;
        this.mDatafilter=mdata;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie2,viewGroup,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder  myViewHolder, int i) {
        myViewHolder.TvTitle.setText(mDatafilter.get(i).getTitle());
        myViewHolder.ImgMovie.setImageResource(mDatafilter.get(i).getThumbnail());
        myViewHolder.Tvtpoint.setText(mDatafilter.get(i).getRating());
        myViewHolder.TvTlabel.setText(mDatafilter.get(i).getLabel());
        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));

    }

    @Override
    public int getItemCount() {
        return mDatafilter.size();

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key=constraint.toString();
                if(key.isEmpty())
                {
                    mDatafilter=mData;
                }
                else
                {
                    List<MovieSapChieu>lstFitler=new ArrayList<>();
                    for(MovieSapChieu row:mData)
                    {
                        if(row.getTitle().toLowerCase().contains(key.toLowerCase())||row.getTitle().toUpperCase().contains(key.toUpperCase()))
                        {
                            lstFitler.add(row);
                        }
                    }
                    mDatafilter=lstFitler;


                }
                FilterResults filterResults= new FilterResults();
                filterResults.values=mDatafilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDatafilter=(List<MovieSapChieu>)results.values;
                notifyDataSetChanged();
            }

        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle,Tvtpoint,TvTlabel;
        private ImageView ImgMovie,imgsao;

        public RelativeLayout container;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvTitle= itemView.findViewById(R.id.item_movie_title);
            ImgMovie=itemView.findViewById(R.id.item_movie_img);
            Tvtpoint=itemView.findViewById(R.id.item_movie_point);
            TvTlabel=itemView.findViewById(R.id.item_movie_label);
            //imgsao=itemView.findViewById(R.id.item_movie_star);
            container = itemView.findViewById(R.id.container);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieSapchieuClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}

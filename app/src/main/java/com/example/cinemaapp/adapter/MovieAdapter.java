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
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.MovieItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {
    Context context;
    List<Movie> mData;
    List<Movie> mDatafilter;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(List<Movie> mData,Context context) {
        this.context=context;
        this.mDatafilter =mData;
        this.mData=mData;

    }

    public MovieAdapter(Context context, List<Movie> mdata, MovieItemClickListener listener) {
            this.context = context;
            this.mData = mdata;
            this.movieItemClickListener = listener;
            this.mDatafilter=mdata;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

      //  MyViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));

        // lets create the animation for the whole card
        // first lets create a reference to it
        // you ca use the previous same animation like the following

        // but i want to use a different one so lets create it ..
       // MyViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        myViewHolder.TvTitle.setText(mDatafilter.get(i).getTitle());
        myViewHolder.ImgMovie.setImageResource(mDatafilter.get(i).getThumbnail());
        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
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
                   List<Movie>lstFitler=new ArrayList<>();
                   for(Movie row:mData)
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
                mDatafilter=(List<Movie>)results.values;
                notifyDataSetChanged();
           }
       };
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle,Tvtpoint;
        private ImageView ImgMovie,imgsao;

        public RelativeLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TvTitle= itemView.findViewById(R.id.item_movie_title);
            ImgMovie=itemView.findViewById(R.id.item_movie_img);
            Tvtpoint=itemView.findViewById(R.id.item_movie_point);
            imgsao=itemView.findViewById(R.id.item_movie_star);
            container = itemView.findViewById(R.id.container);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),ImgMovie);
                }
            });
        }
    }
}

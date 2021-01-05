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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {
    Context context;
    LinkedList<Movie> mData;
    LinkedList<Movie> mDatafilter;
   MovieItemClickListener movieItemClickListener;
    LayoutInflater inflater;
    public MovieAdapter(LinkedList<Movie> mData, Context context,MovieItemClickListener listener) {
        this.mData=mData;
        this.context=context;
        this.movieItemClickListener=listener;
        inflater= LayoutInflater.from(context);
        this.mDatafilter =mData;

    }

//    public MovieAdapter(Context context, LinkedList<Movie> mdata, MovieItemClickListener listener) {
//            this.context = context;
//            this.mData = mdata;
//            this.movieItemClickListener = listener;
//            this.mDatafilter=mdata;
//    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView= inflater.inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder (itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

      //  MyViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));

        // lets create the animation for the whole card
        // first lets create a reference to it
        // you ca use the previous same animation like the following

        // but i want to use a different one so lets create it ..
        //MyViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
//        myViewHolder.TvTitle.setText(mDatafilter.get(i).getTitle());
//        myViewHolder.ImgMovie.setImageResource(mDatafilter.get(i).getThumbnail());
//        myViewHolder.Tvtpoint.setText(mDatafilter.get(i).getRating());
//        myViewHolder.TvTlabel.setText(mDatafilter.get(i).getLabel());

        Movie movie=mData.get(i);
        myViewHolder.TvTitle.setText(movie.getTitle());
        myViewHolder.Tvtpoint.setText(movie.getRating());
        myViewHolder.TvTlabel.setText(movie.getLabel());
        Picasso.get().load(movie.getCoverPhoto()).into( myViewHolder.ImgMovie);
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
                   LinkedList<Movie>lstFitler=new LinkedList<>();
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
                mDatafilter=(LinkedList<Movie>)results.values;
                notifyDataSetChanged();
           }
       };
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView TvTitle,Tvtpoint,TvTlabel;
        private ImageView ImgMovie;
        private MovieAdapter adapter;
        public RelativeLayout container;

        public MyViewHolder(@NonNull View itemView , MovieAdapter movieAdapter) {
            super(itemView);

            TvTitle= itemView.findViewById(R.id.item_movie_title);
            ImgMovie=itemView.findViewById(R.id.item_movie_img);
            Tvtpoint=itemView.findViewById(R.id.item_movie_point);
            TvTlabel=itemView.findViewById(R.id.item_movie_label);
            adapter=movieAdapter;
            //imgsao=itemView.findViewById(R.id.item_movie_star);
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

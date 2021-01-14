package com.example.cinemaapp.adapter;



import android.content.Context;
import android.content.Intent;
import android.net.wifi.rtt.WifiRttManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Lichchieu;
import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.MovieItemClickListener;
import com.example.cinemaapp.ui.GiaodienActivity;
import com.example.cinemaapp.ui.SoDoRapActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.MyViewHolder>{
    Context context;
    public LinkedList<Lichchieu> mDataTatCa;
    public LinkedList<Lichchieu> mDataLoc;


    public LichChieuAdapter(Context context,LinkedList<Lichchieu> mdataLoc) {
        this.context = context;

        this.mDataTatCa=mdataLoc;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {



        return new LichChieuAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suat_chieu, viewGroup, false),this);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {



            Lichchieu lichchieu = mDataTatCa.get(i);

            myViewHolder.btn_ChonSuatChieu.setText(lichchieu.SuatChieu);

            myViewHolder.btn_ChonSuatChieu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Toast.makeText(context,mDataTatCa.get(i).SuatChieu,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, SoDoRapActivity.class);
                    Bundle bundle = new Bundle();
                        Gson gson = new Gson();
                        String s = gson.toJson(lichchieu);
                        intent.putExtra("LichChieu" ,s);

                   context.startActivity(intent);

                }
            });



    }

    @Override
    public int getItemCount() {
        return mDataTatCa.size();
    }



    public  class MyViewHolder extends RecyclerView.ViewHolder {

        private Button btn_ChonSuatChieu;
        final  LichChieuAdapter lichChieuAdapter;

        public MyViewHolder(@NonNull View itemView, LichChieuAdapter mlichChieu) {
            super(itemView);
            this.lichChieuAdapter = mlichChieu;
            btn_ChonSuatChieu= itemView.findViewById(R.id.btn_ChonSuatChieu);

        }
    }
}


package com.example.cinemaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Ve;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

public class VeAdapter extends RecyclerView.Adapter<VeAdapter.VeViewHolder> {
    private LinkedList<Ve> mVeList;
    private LayoutInflater mInflater;
    private NumberFormat format = new DecimalFormat("#,###");

    public VeAdapter(Context context, LinkedList<Ve> mVeList) {
        mInflater = LayoutInflater.from(context);
        this.mVeList = mVeList;
    }

    @NonNull
    @Override
    public VeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_ve, parent, false);
        return new VeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull VeViewHolder holder, int position) {
        Ve mVe = mVeList.get(position);
        holder.tenPhim.setText(mVe.getTenPhim());
        holder.chiNhanh.setText(mVe.getChiNhanh());
        holder.ghe.setText("Ghế: " + mVe.getGhe());
        holder.diem.setText(String.valueOf("+" + mVe.getDiem() + " điểm thưởng"));
        Double giaTien = mVe.getGiaTien();
        holder.giaTien.setText(format.format(giaTien) + " VNĐ");
        holder.thoiGianMua.setText(mVe.getThoiGianMua());
    }

    @Override
    public int getItemCount() {
        return mVeList.size();
    }

    public class VeViewHolder extends RecyclerView.ViewHolder {
        public TextView tenPhim;
        public TextView chiNhanh;
        public TextView ghe;
        public TextView diem;
        public TextView giaTien;
        public TextView thoiGianMua;
        final VeAdapter mAdapter;

        public VeViewHolder(@NonNull View itemView, VeAdapter mAdapter) {
            super(itemView);
            this.tenPhim = itemView.findViewById(R.id.txt_FilmName);
            this.chiNhanh = itemView.findViewById(R.id.txt_TheaterName);
            this.ghe = itemView.findViewById(R.id.txt_Chair);
            this.diem = itemView.findViewById(R.id.txt_Point);
            this.giaTien = itemView.findViewById(R.id.txt_Price);
            this.thoiGianMua = itemView.findViewById(R.id.txt_DateTime);
            this.mAdapter = mAdapter;
        }
    }
}

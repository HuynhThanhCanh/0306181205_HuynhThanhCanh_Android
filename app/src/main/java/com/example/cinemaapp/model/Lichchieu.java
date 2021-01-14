package com.example.cinemaapp.model;

import java.sql.Time;
import java.util.Date;

public class Lichchieu {
    public String MaLichChieu;
    public String MaPhim;
    public String MaSuatChieu;
    public String MaRap;
    public String NgayChieu;
    public  String TenPhim;
    public String SuatChieu;

    public Lichchieu() {
    }

    public Lichchieu(String maLichchieu, String maPhim, String maSuatChieu, String ngayChieu, String tenPhim, String suatChieu) {
        MaLichChieu = maLichchieu;
        MaPhim = maPhim;
        MaSuatChieu = maSuatChieu;
        NgayChieu = ngayChieu;
        TenPhim = tenPhim;
        SuatChieu = suatChieu;
    }
}

package com.example.cinemaapp.model;

public class Ve {
    private String tenPhim;
    private String chiNhanh;
    private double giaTien;
    private String ghe;
    private float diem;
    private String thoiGianMua;

    public Ve() {
    }

    public Ve(String tenPhim, String chiNhanh, double giaTien, String ghe, float diem, String thoiGianMua) {
        this.tenPhim = tenPhim;
        this.chiNhanh = chiNhanh;
        this.giaTien = giaTien;
        this.ghe = ghe;
        this.diem = diem;
        this.thoiGianMua = thoiGianMua;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }

    public String getThoiGianMua() {
        return thoiGianMua;
    }

    public void setThoiGianMua(String thoiGianMua) {
        this.thoiGianMua = thoiGianMua;
    }
}

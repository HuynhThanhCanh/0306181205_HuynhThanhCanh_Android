package com.example.cinemaapp.model;

public class ThanhVien {
    private int mId;
    private String mHoten;
    private String mSDT;
    private String mEmail;
    private String mMatKhau;
    private String mNgaysinh;
    private String mGioitinh;
    private String mDiaChi;

    public ThanhVien(String hoten, String sdt, String email, String matkhau, String gioitinh, String ngaysinh) {

    }

    public ThanhVien(String mHoten, String mSDT, String mEmail, String mMatKhau, String mNgaysinh, String mGioitinh, String mDiaChi) {
        this.mHoten = mHoten;
        this.mSDT = mSDT;
        this.mEmail = mEmail;
        this.mMatKhau = mMatKhau;
        this.mNgaysinh = mNgaysinh;
        this.mGioitinh = mGioitinh;
        this.mDiaChi = mDiaChi;
    }

    public ThanhVien(int mId, String mHoten, String mSDT, String mEmail, String mMatKhau, String mNgaysinh, String mGioitinh, String mDiaChi) {
        this.mId = mId;
        this.mHoten = mHoten;
        this.mSDT = mSDT;
        this.mEmail = mEmail;
        this.mMatKhau = mMatKhau;
        this.mNgaysinh = mNgaysinh;
        this.mGioitinh = mGioitinh;
        this.mDiaChi = mDiaChi;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmHoten() {
        return mHoten;
    }

    public void setmHoten(String mHoten) {
        this.mHoten = mHoten;
    }

    public String getmSDT() {
        return mSDT;
    }

    public void setmSDT(String mSDT) {
        this.mSDT = mSDT;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmNgaysinh() {
        return mNgaysinh;
    }

    public void setmNgaysinh(String mNgaysinh) {
        this.mNgaysinh = mNgaysinh;
    }

    public String getmGioitinh() {
        return mGioitinh;
    }

    public void setmGioitinh(String mGioitinh) {
        this.mGioitinh = mGioitinh;
    }

    public String getmDiaChi() {
        return mDiaChi;
    }

    public void setmDiaChi(String mDiaChi) {
        this.mDiaChi = mDiaChi;
    }
}

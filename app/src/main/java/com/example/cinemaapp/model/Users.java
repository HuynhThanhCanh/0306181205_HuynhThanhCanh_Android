package com.example.cinemaapp.model;

import java.util.Date;

public class Users {
    private String email;
    private  String pass;
    private  String HoTenTV;
    private String NgaySinh;
    private String DiaChi;
    private  String SDT;
    private String Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Users(String email, String pass, String hoTenTV, String ngaySinh, String diaChi, String SDT) {
        this.email = email;
        this.pass = pass;
        HoTenTV = hoTenTV;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public Users(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Users()
    {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHoTenTV() {
        return HoTenTV;
    }

    public void setHoTenTV(String hoTenTV) {
        HoTenTV = hoTenTV;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}

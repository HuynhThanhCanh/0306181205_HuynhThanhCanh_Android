package com.example.cinemaapp.model;

public class Address {
    private String city;
    private String district;
    private String award;

    public Address() {
    }

    public Address(String city, String district, String award) {
        this.city = city;
        this.district = district;
        this.award = award;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}

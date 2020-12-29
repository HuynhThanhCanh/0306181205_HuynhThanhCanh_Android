package com.example.cinemaapp.model;

public class MovieSapChieu {
    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String streamingLink;
    private int coverPhoto;
    private String label;



    public  MovieSapChieu(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public  MovieSapChieu(String description, String studio, String rating, String streamingLink, String label) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = streamingLink;
        this.label=label;

    }

    public  MovieSapChieu(String title, int thumbnail, int coverPhoto, String rating, String label) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto=coverPhoto;
        this.rating=rating;
        this.label=label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}

package com.example.cinemaapp.model;

public class Movie {
    private String title;
    private String description;
    private String thumbnail;
    private String genre;
    private  String Directors;
    private String rating;
    private String streamingLink;
    private String coverPhoto;
    private String label;



//    public Movie(String title, String thumbnail) {
//        this.title = title;
//        this.thumbnail = thumbnail;
//    }
//
//    public Movie(String description, String studio, String rating, String streamingLink, String label) {
//        this.title = title;
//        this.description = description;
//        this.thumbnail = thumbnail;
//        this.studio = studio;
//        this.rating = rating;
//        this.streamingLink = streamingLink;
//        this.label=label;
//
//    }

//    public Movie(String title, String thumbnail, String coverPhoto, String rating, String label) {
//        this.title = title;
//        this.thumbnail = thumbnail;
//        this.coverPhoto=coverPhoto;
//        this.rating=rating;
//        this.label=label;
//    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie() {

    }

    public String getDirectors() {
        return Directors;
    }

    public void setDirectors(String directors) {
        Directors = directors;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public String getRating() {
        return rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}

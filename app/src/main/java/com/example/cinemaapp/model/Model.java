package com.example.cinemaapp.model;

public class Model {

    private  String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Model(String id, String imageURL, String title, String description) {
        Id = id;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
    }

    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Model(String imageURL, String title, String description) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
    }

    private int image;
    private String title;
    private String description;

    public Model(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

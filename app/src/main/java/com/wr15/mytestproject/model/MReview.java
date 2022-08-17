package com.wr15.mytestproject.model;

import com.google.gson.annotations.SerializedName;

public class MReview {

    @SerializedName("username")
    String username;
    @SerializedName("rating")
    private double rating;
    @SerializedName("content")
    String content;

    @SerializedName("rating_null")
    private double rating_null;


    public double getRating_null() {
        return rating_null;
    }

    public void setRating_null(double rating_null) {
        this.rating_null = rating_null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

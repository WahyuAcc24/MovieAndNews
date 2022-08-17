package com.wr15.mytestproject.model;

import com.google.gson.annotations.SerializedName;

public class MGenre {


    @SerializedName("name")
    String name;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

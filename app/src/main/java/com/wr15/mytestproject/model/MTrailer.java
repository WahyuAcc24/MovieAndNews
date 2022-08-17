package com.wr15.mytestproject.model;

import com.google.gson.annotations.SerializedName;

public class MTrailer {

    @SerializedName("key")
    String key;
    @SerializedName("type")
    String type;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

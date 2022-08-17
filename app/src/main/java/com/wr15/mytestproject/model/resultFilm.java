package com.wr15.mytestproject.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class resultFilm<N> {
    @SerializedName("page") private String page;
    @SerializedName("results") private List<N> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<N> getResults() {
        return results;
    }

    public void setResults(List<N> results) {
        this.results = results;
    }
}

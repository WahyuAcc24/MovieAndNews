package com.wr15.mytestproject.model

import com.google.gson.annotations.SerializedName

class MNews {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("publishedAt")
    var publishedAt: String? = null
}
package com.wr15.mytestproject.model

import com.google.gson.annotations.SerializedName

class MReview {
    @SerializedName("username")
    var username: String? = null

    @SerializedName("rating")
    var rating = 0.0

    @SerializedName("content")
    var content: String? = null

    @SerializedName("rating_null")
    var rating_null = 0.0
}
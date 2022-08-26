package com.wr15.mytestproject.model

import com.google.gson.annotations.SerializedName

class MFilm {
    @SerializedName("id")
    var id = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_average")
    var vote_average = 0.0

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("release_date")
    var release_date: String? = null

    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

    @SerializedName("popularity")
    var popularity: String? = null
}
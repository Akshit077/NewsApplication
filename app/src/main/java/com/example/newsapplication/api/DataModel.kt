package com.example.newsapplication.api

import com.google.gson.annotations.SerializedName

data class DataModel(

    @SerializedName("title")
    val title : String,

    @SerializedName("author")
    val author: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("image")
    val image: String
)
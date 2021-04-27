package com.example.newsapplication

import com.google.gson.annotations.SerializedName

data class ResponseDataModel (
    @SerializedName("data")
    val data: ArrayList<DataModel>
)
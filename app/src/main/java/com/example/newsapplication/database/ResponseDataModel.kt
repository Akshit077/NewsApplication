package com.example.newsapplication.database

import com.example.newsapplication.api.DataModel
import com.google.gson.annotations.SerializedName

data class ResponseDataModel (
    @SerializedName("data")
    val data: ArrayList<DataModel>
)
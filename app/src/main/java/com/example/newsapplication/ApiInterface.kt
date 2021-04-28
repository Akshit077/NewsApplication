package com.example.newsapplication

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("news")
    fun getData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,
        @Query("categories") cat:String
    ):retrofit2.Call<ResponseDataModel>
    @GET("news")
    fun getSearchData(
            @Query("access_key")key:String,
            @Query("languages")lang:String,
            @Query("keywords")word:String
    ):retrofit2.Call<ResponseDataModel>
}
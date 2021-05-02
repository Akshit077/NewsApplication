package com.example.newsapplication.api

import com.example.newsapplication.database.ResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("news")
    fun getLiveData(
            @Query("access_key") key : String,
            @Query("languages") lang: String
    ):retrofit2.Call<ResponseDataModel>

    @GET("news")
    fun getData(
        @Query("access_key") key : String,
        @Query("languages") lang: String,
        @Query("categories") cat:String
    ):retrofit2.Call<ResponseDataModel>
    @GET("news")
    fun getSearchData(
            @Query("access_key")key:String,
            @Query("keywords")word:String,
            @Query("languages")lang:String,
            @Query("countries")country:String
    ):retrofit2.Call<ResponseDataModel>
    @GET("news")
    fun getCountrySpecificData(
            @Query("access_key")key:String,
            @Query("languages")lang:String,
            @Query("countries")country:String
    ):retrofit2.Call<ResponseDataModel>
    @GET("news")
    fun getLanguageSpecificData(
            @Query("access_key")key:String,
            @Query("languages")lang:String
    ):retrofit2.Call<ResponseDataModel>
}
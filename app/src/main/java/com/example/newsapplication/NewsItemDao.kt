package com.example.newsapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsItemDao {
    @Query("SELECT * FROM savedNewsItem_table")
    fun getNewsDetails(): LiveData<List<SavedItemDataClass>>

    @Insert
    fun insertNewsDetails(user:SavedItemDataClass)
}
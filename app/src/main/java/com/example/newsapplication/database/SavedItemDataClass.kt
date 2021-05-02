package com.example.newsapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedNewsItem_table")
data class SavedItemDataClass(
    @PrimaryKey(autoGenerate = true)
    val newsId: Int?,
    @ColumnInfo(name = "news_title")
    val newsTitle: String?,
    @ColumnInfo(name = "news_description")
    val newsDescription: String?
)
package com.example.newsapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapplication.database.NewsItemDao
import com.example.newsapplication.database.SavedItemDataClass

@Database(entities = [SavedItemDataClass::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : NewsItemDao
}
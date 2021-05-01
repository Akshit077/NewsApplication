package com.example.newsapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedItemDataClass::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : NewsItemDao
}
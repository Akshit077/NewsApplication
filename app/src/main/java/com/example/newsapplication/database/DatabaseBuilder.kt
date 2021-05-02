package com.example.newsapplication.database

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE =
                    buildRoomDB(
                        context
                    )
            }
        }
        return INSTANCE!!
    }
    /*
    function to create room database
     */
    private fun buildRoomDB(context: Context): AppDatabase? {
        return Room.databaseBuilder(context, AppDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration().build()
    }
}
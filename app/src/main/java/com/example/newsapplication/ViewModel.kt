package com.example.newsapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var user: LiveData<List<SavedItemDataClass>> = MutableLiveData()
    private val roomDatabaseBuilder = DatabaseBuilder.getInstance(context)

    init {
        // to getAllUserDetails()
        user = roomDatabaseBuilder.userDao().getNewsDetails()

    }
    fun addUserDetails(savedItemDataClass: SavedItemDataClass) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.userDao().insertNewsDetails(
                SavedItemDataClass(newsId = savedItemDataClass.newsId,
                    newsTitle = savedItemDataClass.newsTitle,
                    newsDescription = savedItemDataClass.newsDescription
                    //newsImage = savedItemDataClass.newsImage
                )
            )
        }
    }
}
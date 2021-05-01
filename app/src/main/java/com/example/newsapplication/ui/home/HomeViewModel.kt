package com.example.newsapplication.ui.home

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.DatabaseBuilder
import com.example.newsapplication.DetailedNews
import com.example.newsapplication.SavedItemDataClass
import java.util.concurrent.Executors

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var user: LiveData<List<SavedItemDataClass>> = MutableLiveData()
    private val roomDatabaseBuilder = DatabaseBuilder.getInstance(context)

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/

    init {
        // to getAllUserDetails()
        user = roomDatabaseBuilder.userDao().getNewsDetails()

    }

    fun addUserDetails(savedItemDataClass: SavedItemDataClass) {
        Executors.newSingleThreadExecutor().execute {
            roomDatabaseBuilder.userDao().insertNewsDetails(
                SavedItemDataClass(
                    newsId = savedItemDataClass.newsId,
                    newsTitle = savedItemDataClass.newsTitle,
                    newsDescription = savedItemDataClass.newsDescription
                    //newsImage = savedItemDataClass.newsImage
                )
            )
        }
    }
}
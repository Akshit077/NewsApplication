package com.example.newsapplication.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newsapplication.R

class DetailedNews : AppCompatActivity() {

    private val sharedPrefFile="SharedPrefDemo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_news)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        /*Creating shared preference to store url,title
           and description
         */
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        //Getting url,titleNews,descriptionNews from Main Activity

        val urlNews=intent.getStringExtra("get_news_url")
        val titleNews=intent.getStringExtra("get_news_title")
        val descriptionNews=intent.getStringExtra("get_news_desc")

        Log.e("IntentNews","Title $titleNews")
        Log.e("IntentNews","Desc $descriptionNews")

        //Saving the url in SharedPreferences for further use
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("url_News",urlNews)
        editor.putString("title_News",titleNews)
        editor.putString("description_News",descriptionNews)
        editor.apply()
        editor.commit()

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)
    }
}
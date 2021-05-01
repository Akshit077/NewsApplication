package com.example.newsapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class DetailedNews : AppCompatActivity() {

    private val sharedPrefFile="SharedPrefDemo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_news)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        /*Getting url from Main Activity*/
        val urlNews=intent.getStringExtra("get_news_url")
        val titleNews=intent.getStringExtra("get_news_title")
        val descriptionNews=intent.getStringExtra("get_news_desc")
        //val imageNews=intent.getStringExtra("get_news_image")

        Log.e("IntentNews","Title $titleNews")
        Log.e("IntentNews","Desc $descriptionNews")
       // Log.e("IntentNews","Url $imageNews")

        //Saving the url in SharedPreferences for further use
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("url_News",urlNews)
        editor.putString("title_News",titleNews)
        editor.putString("description_News",descriptionNews)
        editor.apply()
        editor.commit()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
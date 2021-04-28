package com.example.newsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        searchNews()
        actionOnImageClick()
    }
    private fun actionOnImageClick() {
        business.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "business")
            startActivity(intent)
        })
        entertainment.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "entertainment")
            startActivity(intent)
        })
        health.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "health")
            startActivity(intent)
        })
        science.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "science")
            startActivity(intent)
        })
        sports.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "sports")
            startActivity(intent)
        })
        technology.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "technology")
            startActivity(intent)
        })
    }
    private fun searchNews()
    {
        news_search_button.setOnClickListener(View.OnClickListener {
            val customSearch=search_news.query.toString()
            Toast.makeText(this,customSearch,Toast.LENGTH_SHORT).show()
            val intentSearchBar=Intent(this,MainActivity::class.java)
            intentSearchBar.putExtra("keywords",customSearch)
            intentSearchBar.putExtra("check",true)
            startActivity(intentSearchBar)
        })
    }
    }
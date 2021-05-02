package com.example.newsapplication.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.newsapplication.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {

    private var countryValue = ""
    private var languageValue = ""
    private val sharedPrefUse="SharedPrefFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefUse, Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        searchNews()
        actionOnImageClick()
        inflateCountriesDropDownData(editor)
        inflateLanguageDropDownData(editor)
    }

    private fun searchNews() {
        news_search_button.setOnClickListener(View.OnClickListener {
            val customSearch = search_news.query.toString()
            Toast.makeText(this, customSearch, Toast.LENGTH_SHORT).show()
            val intentSearchBar = Intent(this, MainActivity::class.java)
            intentSearchBar.putExtra("keywords", customSearch)
            intentSearchBar.putExtra("check", true)
            startActivity(intentSearchBar)
        })
    }


    private fun actionOnImageClick() {
        business.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("categories", "business")
            intent.putExtra("cat",true)
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


    private fun inflateCountriesDropDownData(editor:SharedPreferences.Editor) {
        val countries = resources.getStringArray(R.array.countries)
        val arrayAdapter =
            ArrayAdapter(applicationContext,
                R.layout.dropdown_item_country, countries)
        autoCompleteTextView_Country.setAdapter(arrayAdapter)

        autoCompleteTextView_Country.setOnItemClickListener { parent, view, position, id ->

            val countrySelected = parent.getItemAtPosition(position)
            if (countrySelected.equals("")) {
                countryValue = "us"
            } else {
                when (countrySelected) {

                    "Argentina" -> countryValue = "ar"
                    "Australia" -> countryValue = "au"
                    "Austria" -> countryValue = "at"
                    "Belgium" -> countryValue = "be"
                    "Brazil" -> countryValue = "br"
                    "Bulgaria" -> countryValue = "bg"
                    "Canada" -> countryValue = "ca"
                    "China" -> countryValue = "cn"
                    "Colombia" -> countryValue = "co"
                    "Czech Republic" -> countryValue = "cz"
                    "Egypt" -> countryValue = "eg"
                    "France" -> countryValue = "fr"
                    "Germany" -> countryValue = "de"
                    "Greece" -> countryValue = "gr"
                    "Hong Kong" -> countryValue = "hk"
                    "Hungary" -> countryValue = "hu"
                    "India" -> countryValue = "in"
                    "Indonesia" -> countryValue = "id"
                    "Ireland" -> countryValue = "ie"
                    "Israel" -> countryValue = "il"
                    "Italy" -> countryValue = "it"
                    "Japan" -> countryValue = "jp"
                    "Latvia" -> countryValue = "lv"
                    "Lithuania" -> countryValue = "lt"
                    "Malaysia" -> countryValue = "my"
                    "Mexico" -> countryValue = "mx"
                    "Morocco" -> countryValue = "ma"
                    "Netherlands" -> countryValue = "nl"
                    "New Zealand" -> countryValue = "nz"
                    "Nigeria" -> countryValue = "ng"
                    "Norway" -> countryValue = "no"
                    "Philippines" -> countryValue = "ph"
                    "Poland" -> countryValue = "pl"
                    "Portugal" -> countryValue = "pt"
                    "Romania" -> countryValue = "ro"
                    "Saudi Arabia" -> countryValue = "sa"
                    "Serbia" -> countryValue = "rs"
                    "Singapore" -> countryValue = "sg"
                    "Slovakia" -> countryValue = "sk"
                    "Slovenia" -> countryValue = "si"
                    "South Africa" -> countryValue = "za"
                    "South Korea" -> countryValue = "kr"
                    "Sweden" -> countryValue = "se"
                    "Switzerland" -> countryValue = "ch"
                    "Taiwan" -> countryValue = "tw"
                    "Thailand" -> countryValue = "th"
                    "Turkey" -> countryValue = "tr"
                    "UAE" -> countryValue = "ae"
                    "Ukraine" -> countryValue = "ua"
                    "United Kingdom" -> countryValue = "gb"
                    "United States" -> countryValue = "us"
                    "Venuzuela" -> countryValue = "ve"
                }
            }
            editor.putString("countries",countryValue)
            editor.apply()

            /*val intentCountryBar = Intent(this, MainActivity::class.java)
            intentCountryBar.putExtra("countries", countryValue)
            intentCountryBar.putExtra("checkCountry", true)
//            intentCategoryBar.putExtra("flag" , 3)
            startActivity(intentCountryBar)
            showToast(
                "$countrySelected" +
                        " Country is Selected"
            )*/
        }

    }

    private fun inflateLanguageDropDownData(editor: SharedPreferences.Editor) {
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapter = ArrayAdapter(applicationContext,
            R.layout.dropdown_item_lang, languages)
        autoCompleteTextView_Languages.setAdapter(arrayAdapter)

        //Log.i("languages" , "$languages")

        //to retrieve selected data
        autoCompleteTextView_Languages.setOnItemClickListener { parent, view, position, id ->

            val languageSelected = parent.getItemAtPosition(position)
            if (languageSelected.equals("")) {
                languageValue = "en"
            } else {
                when (languageSelected) {
                    "Arabic" -> languageValue = "ar"
                    "English" -> languageValue = "en"
                    "German" -> languageValue = "de"
                    "Spanish" -> languageValue = "es"
                    "French" -> languageValue = "fr"
                    "Hebrew" -> languageValue = "he"
                    "Italian" -> languageValue = "it"
                    "Dutch" -> languageValue = "nl"
                    "Norwegian" -> languageValue = "no"
                    "Portuguese" -> languageValue = "pt"
                    "Russian" -> languageValue = "ru"
                    "Swedish" -> languageValue = "se"
                    "Chinese" -> languageValue = "zh"
                }
            }
            editor.putString("languages",languageValue)
            editor.apply()
            editor.commit()

            /*val intentLanguageBar = Intent(this, MainActivity::class.java)
            intentLanguageBar.putExtra("languages", languageValue)
            intentLanguageBar.putExtra("checkLanguage", true)
//            intentLanguageBar.putExtra("flag" , 2)
            startActivity(intentLanguageBar)

            showToast(
                "$languageSelected " +
                        " Language is Selected"
            )*/
        }
    }
}
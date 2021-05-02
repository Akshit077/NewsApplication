@file:Suppress("DEPRECATION")
package com.example.newsapplication.activity

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.ItemAdapter
import com.example.newsapplication.R
import com.example.newsapplication.api.ApiClient
import com.example.newsapplication.api.DataModel
import com.example.newsapplication.database.ResponseDataModel
import com.example.newsapplication.extension.showToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
    ItemAdapter.OnItemClickListener {

    private val key="e1d980b2f62d44b6d3f2f6b37fcba98c"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog:ProgressDialog
    var newsList =  ArrayList<DataModel>()
    private var category:String=""
    private var searchBar:String=""
    private var languageBar : String = ""
    private var countryBar : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting an intent from Home screen

        val bundleSearch=intent
        if(bundleSearch.getBooleanExtra("check",false))
        {
            searchBar= bundleSearch.getStringExtra("keywords").toString()
            createProgressDialog()
            setupUI()
            showSpecificNews()
        }
        else if(intent.getBooleanExtra("count",false))
        {
            countryBar= intent.getStringExtra("country").toString()
            createProgressDialog()
            setupUI()
            showCountrySpecificNews()
        }
        else if(intent.getBooleanExtra("lang",false))
        {
            languageBar= intent.getStringExtra("language").toString()
            createProgressDialog()
            setupUI()
            showLanguageSpecificNews()
        }
        else if(intent.getBooleanExtra("cat",false))
        {
            category= bundleSearch.getStringExtra("categories").toString()
            createProgressDialog()
            setupUI()
            showNews()
        }
        else
        {
            createProgressDialog()
            setupUI()
            liveNewsShow()
        }
        }

    private fun setupUI() {
        //recycler view
        val layoutManager = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        //attaching adapter to recycler view
        itemAdapter =
            ItemAdapter(this, newsList, this)
        recyclerView.adapter = itemAdapter
    }

    private fun showNews() {

        progressDialog.show()
            val call = ApiClient.getClient.getData(key, "en",category)
            call.enqueue(object : Callback<ResponseDataModel> {
                override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
                ) {
                    if (response.isSuccessful) {
                        newsList.addAll(response.body()?.data ?: ArrayList())
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                     progressDialog.dismiss()
                }

                override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                     progressDialog.dismiss()
                    Log.e("Failure", "Error is ${t.localizedMessage}")
                    showToast("Check your internet connection")
                }
            })
        }

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data..")
        progressDialog.setCancelable(false)
    }
    private fun showSpecificNews() {

        progressDialog.show()
        val call = ApiClient.getClient.getSearchData(key,searchBar,languageBar,countryBar)
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                call: Call<ResponseDataModel>,
                response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.e("Data", "Data is ${response.body()}\n\n")
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                // progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                showToast("Some Error Occurred while fetching data")
            }
        })
    }
    private fun liveNewsShow()
    {
        progressDialog.show()
        val call = ApiClient.getClient.getLiveData(key, "en")
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                showToast("Check your internet connection")
            }
        })
    }
    private fun showCountrySpecificNews()
    {
        progressDialog.show()
        val call = ApiClient.getClient.getCountrySpecificData(key, "en",countryBar)
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                showToast("Check your internet connection")
            }
        })
    }
    private fun showLanguageSpecificNews()
    {
        progressDialog.show()
        val call = ApiClient.getClient.getLanguageSpecificData(key,languageBar)
        call.enqueue(object : Callback<ResponseDataModel> {
            override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
            ) {
                if (response.isSuccessful) {
                    newsList.addAll(response.body()?.data ?: ArrayList())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<ResponseDataModel>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("Failure", "Error is ${t.localizedMessage}")
                showToast("Check your internet connection")
            }
        })
    }

    override fun onItemClick(position: Int,url_adapter:String,
                             title_adapter:String,desc_adapter:String) {
        val intent=Intent(this,
            DetailedNews::class.java)
        intent.putExtra("get_news_url",url_adapter)
        intent.putExtra("get_news_title",title_adapter)
        intent.putExtra("get_news_desc",desc_adapter)
        startActivity(intent)
    }
}
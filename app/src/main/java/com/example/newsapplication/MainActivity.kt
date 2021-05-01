@file:Suppress("DEPRECATION")
package com.example.newsapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(),ItemAdapter.OnItemClickListener {

    private val KEY="e1d980b2f62d44b6d3f2f6b37fcba98c"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog:ProgressDialog
    var newsList =  ArrayList<DataModel>()
    private var category:String=""
    private var searchBar:String=""
    private var final:String=""


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
        else
        {
            category= bundleSearch.getStringExtra("categories").toString()
            createProgressDialog()
            setupUI()
            showNews()
        }

        }

    private fun setupUI() {
        //recycler view
        val layoutManager = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        //attaching adapter to recycler view
        itemAdapter = ItemAdapter(this,newsList,this)
        recyclerView.adapter = itemAdapter
    }

    private fun showNews() {

        progressDialog.show()
            val call = ApiClient.getClient.getData(KEY, "en",category)
            call.enqueue(object : Callback<ResponseDataModel> {
                override fun onResponse(
                    call: Call<ResponseDataModel>,
                    response: Response<ResponseDataModel>
                ) {
                    if (response.isSuccessful) {
                        newsList.addAll(response.body()?.data ?: ArrayList())
                        recyclerView.adapter?.notifyDataSetChanged()
                        Log.e("Data", "Data is ${response.body()}\n\n")
                        Log.e("Akshit","Title ${response.body()?.data?.get(0)?.title}")
                        Log.e("Akshit","Desc ${response.body()?.data?.get(0)?.description}")
                        Log.e("Akshit","Url ${response.body()?.data?.get(0)?.url}")
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

    private fun createProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading..")
        progressDialog.setMessage("Please wait while we fetch data..")
        progressDialog.setCancelable(false)
    }
    private fun showSpecificNews() {

        progressDialog.show()
        val call = ApiClient.getClient.getSearchData(KEY, "en",searchBar)
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

    override fun onItemClick(position: Int,url_adapter:String,
                             title_adapter:String,desc_adapter:String) {
        val intent=Intent(this,DetailedNews::class.java)
        intent.putExtra("get_news_url",url_adapter)
        intent.putExtra("get_news_title",title_adapter)
        intent.putExtra("get_news_desc",desc_adapter)
        startActivity(intent)
    }
}
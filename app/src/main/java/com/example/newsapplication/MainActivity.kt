@file:Suppress("DEPRECATION")
package com.example.newsapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val KEY="e1d980b2f62d44b6d3f2f6b37fcba98c"
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var progressDialog:ProgressDialog
    var newsList =  ArrayList<DataModel>()
    private var category:String=""
    private var searchBar:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting an intent from Home screen
        val bundle: Bundle? =intent.extras
        category= bundle?.get("categories") as String

        /*val bundleSearch:Bundle?=intent.extras
        searchBar=bundleSearch?.get("keywords")as String*/

        createProgressDialog()

        setupUI()

        showNews()

    }

    private fun setupUI() {
        //recycler view
        val layoutManager = LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        //attaching adapter to recycler view
        itemAdapter = ItemAdapter(this,newsList)
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
}
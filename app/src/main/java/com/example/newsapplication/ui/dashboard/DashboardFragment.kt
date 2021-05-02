package com.example.newsapplication.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.NewsDetailAdapter
import com.example.newsapplication.R
import com.example.newsapplication.database.SavedItemDataClass
import com.example.newsapplication.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private lateinit var itemAdapter: NewsDetailAdapter
    private lateinit var dashboardViewModel: DashboardViewModel
    var newsList =  ArrayList<SavedItemDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        newsDetails_RV.layoutManager = layoutManager
        setupListFromRoom()
        //attaching adapter to recycler view
    }
    private fun setupListFromRoom()
    {
        val application = requireActivity().application
        val userViewModel = ViewModelProvider(this).get(HomeViewModel(application)::class.java)
        userViewModel?.user.observe(viewLifecycleOwner, Observer {
            newsList = it as ArrayList<SavedItemDataClass>
            //Log.e("Item", newsList.toString())
            itemAdapter = NewsDetailAdapter(this, newsList)
            newsDetails_RV.adapter = itemAdapter
            itemAdapter.notifyDataSetChanged()
            Log.e("New", itemAdapter.itemCount.toString())
        })
    }
}
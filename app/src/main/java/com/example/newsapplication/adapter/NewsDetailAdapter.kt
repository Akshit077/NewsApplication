package com.example.newsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.database.SavedItemDataClass
import com.example.newsapplication.ui.dashboard.DashboardFragment

class NewsDetailAdapter(

        newsdataSet1: DashboardFragment,
        private val newsdataSet: ArrayList<SavedItemDataClass>):
    RecyclerView.Adapter<NewsDetailAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.newsTitleTV)
        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_newsdetail, parent, false)
        return ViewHolder(
            inflater
        )
    }

    override fun getItemCount():
            Int = newsdataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = newsdataSet[position].newsTitle
            desc.text = newsdataSet[position].newsDescription

        }
    }
}
package com.example.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.ui.dashboard.DashboardFragment

class NewsDetailAdapter(
    private val context: DashboardFragment,
    private val newsDataset: ArrayList<SavedItemDataClass>):
    RecyclerView.Adapter<NewsDetailAdapter.ViewHolder>() {
   // private val TAG = "UserDetailAdapter"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.newsTitleTV)
        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_newsdetail, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount():
            Int = newsDataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = newsDataset[position].newsTitle
            desc.text = newsDataset[position].newsDescription

        }
    }
}
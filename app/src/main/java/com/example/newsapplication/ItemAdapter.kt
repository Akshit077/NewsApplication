package com.example.newsapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val context: Context, private val dataList : List<DataModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.newsTitleTV)
        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_data,parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = dataList[position].title
            desc.text = dataList[position].description

            Glide.with(context)
                .load(dataList[position].image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }
    }

    override fun getItemCount(): Int = dataList.size
}
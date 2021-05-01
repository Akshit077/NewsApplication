package com.example.newsapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val context: Context, private val dataList : List<DataModel>,private val listener:OnItemClickListener
                  ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view),View.OnClickListener {

        val title: TextView = view.findViewById(R.id.newsTitleTV)
        val desc: TextView = view.findViewById(R.id.newsDescriptionTV)
        val image: ImageView = view.findViewById(R.id.imageView)
        val urldata:TextView=view.findViewById(R.id.url_news)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int=adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position,url_adapter = dataList[position].url,
                title_adapter = dataList[position].title,
                desc_adapter = dataList[position].description)
               // image_adapter = dataList[position].image)
            }
        }

    }
    interface OnItemClickListener
    {
        fun onItemClick(position: Int,url_adapter:String,title_adapter:String,
                        desc_adapter:String)
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
            urldata.text=dataList[position].url
        }
    }

    override fun getItemCount(): Int = dataList.size

}


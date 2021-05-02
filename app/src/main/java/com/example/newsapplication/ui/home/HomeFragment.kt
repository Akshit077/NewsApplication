package com.example.newsapplication.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.R
import com.example.newsapplication.database.SavedItemDataClass
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(/*private val news:NewsItemDao*/) : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    //lateinit var user: List<SavedItemDataClass>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        //Getting the url from shared preference
        val pref: SharedPreferences = requireContext().getSharedPreferences("SharedPrefDemo", 0)
        val data: String? = pref.getString("url_News", null)
        val title_data: String? = pref.getString("title_News", null)
        val description_data: String? = pref.getString("description_News", null)
        Log.e("HomeFragNews", "Title $title_data")
        Log.e("HomeFragNews", "Desc $description_data")

       // user=news.getNewsDetails()
        //Initializing WebView made inside fragment home layout file
        val webView: WebView = root.findViewById(R.id.webView)
        val favourite:ImageView=root.findViewById(R.id.favourite_click)
        /*
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        textView.text = it
        })
        */
        //Loading url coming from shared preference in our home fragment
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
        if (data != null) {
            webView.loadUrl(data)
        }


        favourite.setOnClickListener(View.OnClickListener {
            favourite_click.setColorFilter(resources.getColor(R.color.red))
            val application = requireActivity().application
            val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel(application)::class.java).also {
                    it.addUserDetails(
                        SavedItemDataClass(
                            newsId = null,
                            newsTitle = title_data,
                            newsDescription = description_data
                        )
                    )
                }
            Toast.makeText(activity, "News Saved Successfully", Toast.LENGTH_LONG).show()
        })
        return root
    }
}
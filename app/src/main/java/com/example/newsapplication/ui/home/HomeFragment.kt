package com.example.newsapplication.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

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

        //Initializing WebView made inside fragment home layout file
        val webView:WebView = root.findViewById(R.id.webView)
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
        return root
    }
}
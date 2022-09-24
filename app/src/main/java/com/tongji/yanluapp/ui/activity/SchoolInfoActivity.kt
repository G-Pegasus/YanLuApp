package com.tongji.yanluapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tongji.yanluapp.R

class SchoolInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_info)

        val webView = findViewById<WebView>(R.id.webView)

        val bundle = intent.extras
        val schoolWeb: String = bundle?.getString("data") as String

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.settings.useWideViewPort = true;
        webView.loadUrl(schoolWeb)
    }
}
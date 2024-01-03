package com.tongji.yanluapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.tongji.yanluapp.R
import com.tongji.yanluapp.utils.showToast
import java.net.URISyntaxException


class SchoolInfoActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_info)

        val webView = findViewById<WebView>(R.id.webView)

        val bundle = intent.extras
        val schoolWeb: String = bundle?.getString("data") as String

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            @SuppressLint("WebViewClientOnReceivedSslError")
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler!!.proceed()
            }

            // 实现App跳转
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(
                view: WebView,
                newurl: String
            ): Boolean {
                try {
                    //处理intent协议
                    if (newurl.startsWith("intent://")) {
                        val intent: Intent
                        try {
                            intent = Intent.parseUri(newurl, Intent.URI_INTENT_SCHEME)
                            intent.addCategory("android.intent.category.BROWSABLE")
                            intent.component = null
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                                intent.selector = null
                            }
                            val resolves: List<ResolveInfo> =
                                this@SchoolInfoActivity.packageManager.queryIntentActivities(intent, 0)
                            if (resolves.isNotEmpty()) {
                                startActivityIfNeeded(intent, -1)
                            }
                            return true
                        } catch (e: URISyntaxException) {
                            e.printStackTrace()
                        }
                    }
                    // 处理自定义scheme协议
                    if (!newurl.startsWith("http")) {
                        try {
                            // 以下固定写法
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(newurl)
                            )
                            intent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            startActivity(intent)
                        } catch (e: Exception) {
                            // 防止没有安装的情况
                            e.printStackTrace()
                            this@SchoolInfoActivity.showToast("您所打开的第三方App未安装！")
                        }
                        return true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return super.shouldOverrideUrlLoading(view, newurl)
            }
        }

        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webView.loadUrl(schoolWeb)
    }
}
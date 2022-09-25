package com.tongji.yanluapp.app.network

import android.content.Intent
import com.google.gson.Gson
import com.tongji.yanluapp.ui.activity.MainActivity
import me.hgj.jetpackmvvm.base.appContext
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 15:07
 * @description: Token 过期拦截器
 * @email: tongji0x208@gmail.com
 */

class TokenOutInterceptor : Interceptor {

    private val gson: Gson by lazy { Gson() }

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.body() != null && response.body()!!.contentType() != null) {
            val mediaType = response.body()!!.contentType()
            val string = response.body()!!.string()
            val responseBody = ResponseBody.create(mediaType, string)
            // val apiResponse = gson.fromJson(string, ApiResponse::class.java)
            // 判断逻辑 模拟一下
//            if (apiResponse.errorCode == 99999) {
//                // 如果是普通的activity话 可以直接跳转
//                appContext.startActivity(Intent(appContext, MainActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                })
//            }
            response.newBuilder().body(responseBody).build()
        } else {
            response
        }
    }
}
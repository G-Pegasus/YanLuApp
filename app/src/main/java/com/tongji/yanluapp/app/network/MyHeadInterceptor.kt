package com.tongji.yanluapp.app.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 15:06
 * @description:
 * @email: tongji0x208@gmail.com
 */

class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token123456").build()
        builder.addHeader("device", "Android").build()
        builder.addHeader("isLogin", "").build()
        return chain.proceed(builder.build())
    }

}
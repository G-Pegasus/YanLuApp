package com.tongji.lib_common.network

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

    @kotlin.jvm.Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.body != null && response.body!!.contentType() != null) {
            val mediaType = response.body!!.contentType()
            val string = response.body!!.string()
            val responseBody = ResponseBody.create(mediaType, string)

            response.newBuilder().body(responseBody).build()
        } else {
            response
        }
    }
}
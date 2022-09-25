package com.tongji.yanluapp.app.network

import com.tongji.yanluapp.app.network.response.ImageResponse
import retrofit2.http.GET

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 14:47
 * @description:
 * @email: tongji0x208@gmail.com
 */

interface ApiService {

    companion object {
        const val BASE_URL = "http://cn.bing.com/"
    }

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    suspend fun getSplashImage(): ApiResponse<ArrayList<ImageResponse>>

}
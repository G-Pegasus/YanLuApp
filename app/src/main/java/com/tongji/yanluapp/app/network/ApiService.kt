package com.tongji.yanluapp.app.network

import com.tongji.yanluapp.app.network.response.ImageResponse
import com.tongji.yanluapp.app.network.response.UserInfoResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 14:47
 * @description:
 * @email: tongji0x208@gmail.com
 */

interface ApiService {

    companion object {
        const val BASE_URL = "http://cn.bing.com/"
        const val BASE_URL1 = "http://47.108.173.86:7003/"
    }

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    suspend fun getSplashImage(): ApiResponse<ArrayList<ImageResponse>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("user_number") userNumber: String,
        @Field("user_password") pwd: String
    ): ApiResponse1<UserInfoResponse>
}
package com.tongji.yanluapp.app.network

import com.tongji.yanluapp.app.network.response.AvatarResponse
import com.tongji.yanluapp.app.network.response.ImageResponse
import com.tongji.yanluapp.app.network.response.UserInfoResponse
import me.hgj.jetpackmvvm.network.BaseResponse
import okhttp3.MultipartBody
import retrofit2.http.*


/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 14:47
 * @description:
 * @email: tongji0x208@gmail.com
 */

interface ApiService {

    companion object {
        const val BASE_URL = "http://cn.bing.com/"
        const val BASE_URL1 = "http://47.108.173.86:7001/"
    }

    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    suspend fun getSplashImage(): ApiResponse<ArrayList<ImageResponse>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("user_number") userNumber: String,
        @Field("user_password") pwd: String
    ): ApiResponse1<UserInfoResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("user_name") userName: String,
        @Field("user_number") userNumber: String,
        @Field("user_password") pwd: String
    ): ApiResponse1<Any>

    @Multipart
    @POST("updateHead")
    suspend fun uploadImage(
        @Part avatar: MultipartBody.Part
    ): ApiResponse1<AvatarResponse>

}
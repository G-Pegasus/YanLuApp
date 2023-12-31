package com.tongji.lib_common.network

import com.tongji.lib_common.bean.*
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

    // 更新头像
    @Multipart
    @POST("updateHead")
    suspend fun uploadImage(
        @Part avatar: MultipartBody.Part
    ): ApiResponse1<AvatarResponse>

    // 更新用户信息
    @FormUrlEncoded
    @POST("updateInfo")
    suspend fun updateInfo(
        @Field("user_name") userName: String,
        @Field("user_sign") userSign: String
    ): ApiResponse1<Any>

    // 获取首页轮播图
    @POST("getSlideShowImage")
    suspend fun getBannerImage(): ApiResponse1<BannerImageResponse>

    // 发布帖子
    @Multipart
    @POST("releasePost")
    suspend fun releasePost(
        @Part("post_content") content: String,
        @Part images: List<MultipartBody.Part>
    ): ApiResponse1<Any>

    // 获取帖子
    @POST("getPostList")
    suspend fun getPost(): ApiResponse1<ArrayList<PostData>>

    // 点赞
    @FormUrlEncoded
    @POST("likeStar")
    suspend fun likeStar(
        @Field("post_id") postId: String
    ): ApiResponse1<Any>

    // 获取自己发布的帖子
    @POST("getSelfPosts")
    suspend fun getSelfPosts(): ApiResponse1<ArrayList<PostData>>

    // 删除自己的帖子
    @FormUrlEncoded
    @POST("removePost")
    suspend fun removePost(
        @Field("post_id") postId: String
    ): ApiResponse1<Any>

    // 获取首页推荐
    @POST("getRecommendList")
    suspend fun getRecommendList(): ApiResponse1<ArrayList<ArticleResponse>>

    //获取当日的TODO
    @GET("todo/getTodo")
    suspend fun getTodoByTime(@Query("time")time: String): ApiResponse2<List<TodoResponse>>

    //添加TODO
    @FormUrlEncoded
    @POST("todo/add")
    suspend fun addTodo(
        @Field("title") title: String,
        @Field("time") time: String,
        @Field("detail") detail: String
    ): ApiResponse2<TodoResponse>

    //更新TODO
    @FormUrlEncoded
    @PUT("todo/update")
    suspend fun updateTodo(
        @Field("todo_id") id: String,
        @Field("title") title: String,
        @Field("detail") detail: String,
        @Field("time") time: String
    ): ApiResponse2<TodoResponse>

    //删除TODO
    @DELETE("todo/delete")
    suspend fun deleteTodo(@Query("todo_id") id: String): ApiResponse2<TodoResponse>

}
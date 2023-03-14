package com.tongji.lib_common.network

import me.hgj.jetpackmvvm.network.BaseResponse

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 15:01
 * @description:
 * @email: tongji0x208@gmail.com
 */

data class ApiResponse<T>(val images: T) : BaseResponse<T>() {

    // 这里是示例，wanandroid 网站返回的 错误码为 0 就代表请求成功，请你根据自己的业务需求来改变
    override fun isSucces() = true

    override fun getResponseCode() = 400

    override fun getResponseData() = images

    override fun getResponseMsg() = "errorMsg"
}

data class ApiResponse1<T>(val code: Int, val msg: String, val data: T) : BaseResponse<T>() {

    override fun getResponseCode(): Int = 400

    override fun getResponseData(): T = data

    override fun getResponseMsg(): String = "请先注册或者登录哦"

    override fun isSucces(): Boolean = code == 200

}

data class ApiResponse2<T>(val code:Int,val msg: String,val data: T)
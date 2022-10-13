package com.tongji.yanluapp.app.network

import com.tongji.yanluapp.app.network.response.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import me.hgj.jetpackmvvm.network.AppException

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/26 15:16
 * @description:
 * @email: tongji0x208@gmail.com
 */

val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {
    //注册并登陆
    suspend fun register(username: String,userNumber: String, password: String): ApiResponse1<UserInfoResponse> {
        val registerData = apiService1.register(username, userNumber, password)
        //判断注册结果 注册成功，调用登录接口
        if (registerData.isSucces()) {
            return apiService1.login(userNumber, password)
        } else {
            //抛出错误异常
            throw AppException(registerData.code, registerData.msg)
        }
    }
}
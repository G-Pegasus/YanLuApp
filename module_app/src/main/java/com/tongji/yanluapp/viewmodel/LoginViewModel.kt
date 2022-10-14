package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.lib_common.network.apiService1
import com.tongji.lib_common.bean.UserInfoResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 21:35
 * @description:
 * @email: tongji0x208@gmail.com
 */
class LoginViewModel : BaseViewModel() {

    var loginResult = MutableLiveData<ResultState<UserInfoResponse>>()

    fun loginReq(username: String, password: String) {
        request(
            { apiService1.login(username, password) } //请求体
            , loginResult, // 请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果
            false,
            "正在登录中..."
        )
    }

    fun registerAndLogin(userNumber: String, username: String, password: String) {
        request(
            { HttpRequestCoroutine.register(username, userNumber, password) }
            , loginResult,
            false,
            "正在注册中..."
        )
    }
}
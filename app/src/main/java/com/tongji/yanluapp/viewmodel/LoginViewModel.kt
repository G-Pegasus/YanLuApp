package com.tongji.yanluapp.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.tongji.yanluapp.app.network.apiService1
import com.tongji.yanluapp.app.network.response.UserInfoResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.databind.BooleanObservableField
import me.hgj.jetpackmvvm.callback.databind.StringObservableField
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 21:35
 * @description:
 * @email: tongji0x208@gmail.com
 */
class LoginViewModel : BaseViewModel() {

    //用户名
    var username = StringObservableField()

    //密码(登录注册界面)
    var password = StringObservableField()

    var password2 = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()

    var isShowPwd2 = BooleanObservableField()


    //用户名清除按钮是否显示   不要在 xml 中写逻辑 所以逻辑判断放在这里
    var clearVisible = object : ObservableInt(username){
        override fun get(): Int {
            return if(username.get().isEmpty()){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }

    //密码显示按钮是否显示   不要在 xml 中写逻辑 所以逻辑判断放在这里
    var passwordVisible = object : ObservableInt(password){
        override fun get(): Int {
            return if(password.get().isEmpty()){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }

    //密码显示按钮是否显示   不要在 xml 中写逻辑 所以逻辑判断放在这里
    var passwordVisible2 = object : ObservableInt(password2){
        override fun get(): Int {
            return if(password2.get().isEmpty()){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }

    var loginResult = MutableLiveData<ResultState<UserInfoResponse>>()

    fun loginReq(username: String, password: String) {
        request(
            { apiService1.login(username, password) } //请求体
            , loginResult, // 请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果
            false,
            "正在登录中..."
        )
    }
}
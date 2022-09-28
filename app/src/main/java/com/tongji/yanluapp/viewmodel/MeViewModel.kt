package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.yanluapp.app.network.apiService1
import com.tongji.yanluapp.app.network.response.AvatarResponse
import com.tongji.yanluapp.app.network.response.UpdateInfoResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:24
 * @description:
 * @email: tongji0x208@gmail.com
 */
class MeViewModel : BaseViewModel() {

    var avatarResult = MutableLiveData<ResultState<AvatarResponse>>()
    var updateInfoResult = MutableLiveData<ResultState<UpdateInfoResponse>>()

    // 上传单张图片
    fun uploadAvatar(file: File) {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM) // 表单类型
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        builder.addFormDataPart("file", file.name, requestFile)
        val part = builder.build().part(0)

        request(
            { apiService1.uploadImage(part) } // 请求体
            , avatarResult, // 请求的返回结果
            false, ""
        )
    }

    fun updateInfo(userName: String, userSign: String) {
        request(
            { apiService1.updateInfo(userName, userSign) } //请求体
            , updateInfoResult, // 请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果
            false,
            "正在更新个人信息..."
        )
    }
}
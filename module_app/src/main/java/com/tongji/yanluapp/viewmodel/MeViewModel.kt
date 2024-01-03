package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.luck.picture.lib.entity.LocalMedia
import com.tongji.lib_common.network.apiService1
import com.tongji.lib_common.bean.AvatarResponse
import com.tongji.lib_common.bean.UpdateInfoResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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
    var userInfo = MutableLiveData<UpdateInfoResponse>()

    // 上传单张图片
    fun uploadAvatar(file: LocalMedia) {
        val imageFile = File(file.realPath)
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM) // 表单类型
        val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
        builder.addFormDataPart("file", imageFile.name, requestFile)
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
            , {
                userInfo.value?.userName = userName
                userInfo.value?.userSign = userSign
              }, {},
            false,
            "正在更新个人信息..."
        )
    }
}
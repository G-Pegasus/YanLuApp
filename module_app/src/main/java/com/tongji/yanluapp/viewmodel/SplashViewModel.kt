package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.lib_common.network.apiService
import com.tongji.lib_common.bean.ImageResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 15:26
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SplashViewModel : BaseViewModel() {

    var imageData: MutableLiveData<ResultState<ArrayList<ImageResponse>>> = MutableLiveData()

    fun getSplashImage() {
        request({ apiService.getSplashImage() }, imageData)
    }
}
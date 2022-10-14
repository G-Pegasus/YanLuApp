package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.lib_common.network.apiService1
import com.tongji.lib_common.bean.PostData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:41
 * @description:
 * @email: tongji0x208@gmail.com
 */
class ShareViewModel : BaseViewModel() {

    var postResult = MutableLiveData<ResultState<ArrayList<PostData>>>()

    fun getPost() {
        request(
            { apiService1.getPost() } // 请求体
            , postResult
        )
    }

    fun postLike(postId: String) {
        request(
            { apiService1.likeStar(postId) }, {}, {}
        )
    }
}
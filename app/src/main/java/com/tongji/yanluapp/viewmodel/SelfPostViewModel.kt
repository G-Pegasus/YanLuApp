package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.yanluapp.app.network.apiService1
import com.tongji.yanluapp.app.network.response.PostData
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/6 10:32
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SelfPostViewModel : BaseViewModel() {

    var selfPostResult = MutableLiveData<ResultState<ArrayList<PostData>>>()

    fun getSelfPost() {
        request(
            { apiService1.getSelfPosts() } // 请求体
            , selfPostResult
        )
    }

    fun postLike(postId: String) {
        request(
            { apiService1.likeStar(postId) }, {}, {}
        )
    }
}
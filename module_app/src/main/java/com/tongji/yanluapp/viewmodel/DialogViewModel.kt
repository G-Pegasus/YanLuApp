package com.tongji.yanluapp.viewmodel

import com.tongji.lib_common.network.apiService1
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/6 11:28
 * @description:
 * @email: tongji0x208@gmail.com
 */
class DialogViewModel : BaseViewModel() {

    fun deletePost(postId: String) {
        request(
            { apiService1.removePost(postId) }, {}, {}
        )
    }
}
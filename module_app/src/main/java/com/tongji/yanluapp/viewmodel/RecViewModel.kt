package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tongji.lib_common.network.apiService1
import com.tongji.lib_common.bean.ArticleResponse
import com.tongji.lib_common.bean.BannerImageResponse
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:39
 * @description:
 * @email: tongji0x208@gmail.com
 */
class RecViewModel : BaseViewModel() {

    val bannerResult = MutableLiveData<ResultState<BannerImageResponse>>()
    val articleResult = MutableLiveData<ResultState<ArrayList<ArticleResponse>>>()

    fun getBannerImage() {
        request({ apiService1.getBannerImage() }, bannerResult)
    }

    fun getArticles() {
        request({ apiService1.getRecommendList() }, articleResult)
    }
}
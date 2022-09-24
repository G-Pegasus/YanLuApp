package com.tongji.yanluapp.viewmodel

import androidx.lifecycle.LiveData
import com.tongji.yanluapp.bean.School
import com.tongji.yanluapp.repository.Repository
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/24 18:33
 * @description:
 * @email: tongji0x208@gmail.com
 */
class LikeSchoolViewModel : BaseViewModel() {

    var likeLiveData : LiveData<List<School>> = Repository.loadMyLike()
}
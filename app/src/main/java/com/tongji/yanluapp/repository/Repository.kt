package com.tongji.yanluapp.repository

import androidx.lifecycle.LiveData
import com.tongji.yanluapp.app.dao.LikeSchoolDatabase
import com.tongji.yanluapp.bean.School
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/24 18:30
 * @description:
 * @email: tongji0x208@gmail.com
 */
object Repository {

    private val likeDao = LikeSchoolDatabase.getDatabase(appContext).likeSchoolDao()

    fun loadMyLike() : LiveData<List<School>> {
        return likeDao.loadAllSchools()
    }
}
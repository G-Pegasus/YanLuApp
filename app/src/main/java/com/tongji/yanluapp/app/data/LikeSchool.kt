package com.tongji.yanluapp.app.data

import com.tongji.yanluapp.bean.School

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/24 15:58
 * @description:
 * @email: tongji0x208@gmail.com
 */
object LikeSchool {

    private val likeList = mutableListOf<School>()

    fun addSchool(school: School) {
        likeList.add(school)
    }

    fun getSchool() : MutableList<School> {
        return likeList
    }
}
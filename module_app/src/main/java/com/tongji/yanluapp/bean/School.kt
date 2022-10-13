package com.tongji.yanluapp.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 19:43
 * @description:
 * @email: tongji0x208@gmail.com
 */

@Entity
data class School(
    @PrimaryKey(autoGenerate = false)
    var name: String,
    var image: String,
    var type: String,
    var schoolInfo: String,
    var schoolWeb: String,
    var isLike: Int
)
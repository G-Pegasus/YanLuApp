package com.tongji.lib_common.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/6 19:14
 * @description:
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ArticleResponse(
    var recommend_id: String,
    var recommend_title: String,
    var recommend_url: String,
    var recommend_content: String
) : Parcelable
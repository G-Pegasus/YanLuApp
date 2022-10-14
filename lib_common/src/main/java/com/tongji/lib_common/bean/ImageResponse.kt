package com.tongji.lib_common.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 15:10
 * @description: 闪屏页图片的请求
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class ImageResponse (
    val url: String,
    val copyright: String
) : Parcelable
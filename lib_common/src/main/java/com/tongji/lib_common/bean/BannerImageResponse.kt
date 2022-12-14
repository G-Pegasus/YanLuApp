package com.tongji.lib_common.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/29 15:08
 * @description:
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class BannerImageResponse(
    var fileList : List<String>
) : Parcelable

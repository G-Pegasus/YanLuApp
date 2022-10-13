package com.tongji.yanluapp.app.network.response

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/28 9:08
 * @description:
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class UpdateInfoResponse(
    var userName: String,
    var userSign: String
) : Parcelable
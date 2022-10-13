package com.tongji.yanluapp.app.network.response

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/25 21:32
 * @description:
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class UserInfoResponse(
    var user_name: String,
    var user_number: String,
    var user_sign: String,
    var user_head: String,
    var token: String
) : Parcelable
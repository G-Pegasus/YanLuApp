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
    val user_name: String,
    val user_number: String,
    val user_sign: String,
    val user_head: String,
    val token: String
) : Parcelable
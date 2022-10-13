package com.tongji.yanluapp.app.network.response

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/26 17:42
 * @description:
 * @email: tongji0x208@gmail.com
 */

@SuppressLint("ParcelCreator")
@Parcelize
class AvatarResponse(
    val headUrl: String
) : Parcelable
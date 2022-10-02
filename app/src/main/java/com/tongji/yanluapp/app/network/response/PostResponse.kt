package com.tongji.yanluapp.app.network.response

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 14:07
 * @description:
 * @email: tongji0x208@gmail.com
 */

//@SuppressLint("ParcelCreator")
//@Parcelize
//data class PostResponse(
//    var dataList: List<PostData>
//) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class PostData(
    var post_id: String,
    var user_number: String,
    var post_content: String,
    var post_image: List<String>,
    var post_likes: Int,
    var post_release_time: String,
    var post_replys: String,
    var post_likes_list: List<String>,
    var user_name: String,
    var user_head: String,
    var is_like: Boolean,
    var post_last_time: String
) : Parcelable
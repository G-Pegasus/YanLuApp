package com.tongji.lib_common.bean

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/3/14
 * @Description:
 */
data class TodoResponse(
    val todo_id: String,
    val title: String,
    val detail: String,
    val time: String,
    val user_number: String
)
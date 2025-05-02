package com.tongji.lib_common.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object JsonUtils {
    fun createJsonRequestBody(data: Map<String, Any>): RequestBody {
        val json = Gson().toJson(data)  // 将 Map 转换成 JSON 字符串
        return json.toRequestBody("application/json".toMediaTypeOrNull())  // 转换成 RequestBody
    }
}
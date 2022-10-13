package com.tongji.yanluapp.app.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.app.network.response.UpdateInfoResponse
import com.tongji.yanluapp.app.network.response.UserInfoResponse

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/26 12:15
 * @description: 本地存储工具单例
 * @email: tongji0x208@gmail.com
 */

object CacheUtil {

    /**
     * 获取保存的账户信息
     */
    fun getUser(): UserInfoResponse? {
        val kv = MMKV.mmkvWithID("module_app")
        val userStr = kv.decodeString("user")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, UserInfoResponse::class.java)
        }
    }

    /**
     * 设置账户信息
     */
    fun setUser(userResponse: UserInfoResponse?) {
        val kv = MMKV.mmkvWithID("module_app")
        if (userResponse == null) {
            kv.encode("user", "")
            setIsLogin(false)
        } else {
            kv.encode("user", Gson().toJson(userResponse))
            setIsLogin(true)
        }
    }

    fun setUserInfo(updateInfo: UpdateInfoResponse?) {
        val kv = MMKV.mmkvWithID("module_app")
        if (updateInfo == null) {
            kv.encode("userInfo", "")
        } else {
            kv.encode("userInfo", Gson().toJson(updateInfo))
        }
    }

    fun getUserInfo() : UpdateInfoResponse? {
        val kv = MMKV.mmkvWithID("module_app")
        val userStr = kv.decodeString("userInfo")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, UpdateInfoResponse::class.java)
        }
    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.mmkvWithID("module_app")
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("module_app")
        kv.encode("login", isLogin)
    }
}
package com.tongji.yanluapp.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import com.umeng.commonsdk.UMConfigure
import me.hgj.jetpackmvvm.base.appContext

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        MMKV.initialize(this)

        UMConfigure.preInit(appContext, "659606f6a7208a5af195d5e1", "Umeng")
        UMConfigure.init(appContext, "659606f6a7208a5af195d5e1", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
    }
} 
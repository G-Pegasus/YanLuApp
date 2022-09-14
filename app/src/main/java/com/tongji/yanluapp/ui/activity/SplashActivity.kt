package com.tongji.yanluapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tongji.yanluapp.R

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:08
 * @description:
 * @email: tongji0x208@gmail.com
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
package com.tongji.yanluapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.tongji.yanluapp.databinding.ActivitySplashBinding
import com.tongji.yanluapp.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import com.tongji.lib_base.ui.BaseActivity1
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:08
 * @description: 闪屏页
 * @email: tongji0x208@gmail.com
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity1<SplashViewModel, ActivitySplashBinding>() {

    //请求数据ViewModel
    private val imageViewModel: SplashViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        imageViewModel.getSplashImage()
        mViewBind.ivSplash.alpha = 0f
        mViewBind.ivSplash.visibility = View.VISIBLE
        mViewBind.tvDes.alpha = 0f
        mViewBind.tvDes.visibility = View.VISIBLE
        imageViewModel.run {
            imageData.observe(this@SplashActivity) { it ->
                parseState(it, {
                    Glide.with(this@SplashActivity).load("https://cn.bing.com${it[0].url}").into(mViewBind.ivSplash)
                    mViewBind.ivSplash.animate()
                        .alpha(1f).duration = 1000
                    mViewBind.ivSplash.animate()
                        .scaleX(1.1f)
                        .scaleY(1.1f).duration = 3000
                    mViewBind.tvDes.text = it[0].copyright
                    mViewBind.tvDes.animate().alpha(1f).duration = 1000
                })
            }
        }

        actionBar?.hide()
        lifecycleScope.launchWhenCreated {
            delay(4000)
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }
}
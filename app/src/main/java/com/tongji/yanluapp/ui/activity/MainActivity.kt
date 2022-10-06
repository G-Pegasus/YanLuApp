package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.utils.init
import com.tongji.yanluapp.app.utils.initMain
import com.tongji.yanluapp.databinding.ActivityMainBinding
import com.tongji.yanluapp.viewmodel.MainViewModel
import com.tongji.yanluapp.app.base.BaseActivity1

class MainActivity : BaseActivity1<MainViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        // 初始化 ViewPager
        mViewBind.mainViewpager.initMain(this)

        // 初始化 BottomNavigationView
        mViewBind.navView.init {
            when (it) {
                R.id.navigation_home -> mViewBind.mainViewpager.setCurrentItem(0, false)
                // R.id.navigation_subject -> mViewBind.mainViewpager.setCurrentItem(1, false)
                R.id.navigation_info -> mViewBind.mainViewpager.setCurrentItem(1, false)
                R.id.navigation_mine -> mViewBind.mainViewpager.setCurrentItem(2, false)
            }
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.tongji.lib_base.ui.BaseActivity1
import com.tongji.yanluapp.R
import com.tongji.yanluapp.databinding.ActivityMainBinding
import com.tongji.yanluapp.utils.init
import com.tongji.yanluapp.utils.initMain
import com.tongji.yanluapp.utils.showToast
import com.tongji.yanluapp.viewmodel.MainViewModel

class MainActivity : BaseActivity1<MainViewModel, ActivityMainBinding>() {

    private var mExitTime = System.currentTimeMillis()

    override fun initView(savedInstanceState: Bundle?) {
        // 初始化 ViewPager
        mViewBind.mainViewpager.initMain(this)

        // 初始化 BottomNavigationView
        mViewBind.navView.init {
            when (it) {
                R.id.navigation_home -> mViewBind.mainViewpager.setCurrentItem(0, false)
                R.id.navigation_subject -> mViewBind.mainViewpager.setCurrentItem(3, false)
                R.id.navigation_info -> mViewBind.mainViewpager.setCurrentItem(1, false)
                R.id.navigation_mine -> mViewBind.mainViewpager.setCurrentItem(2, false)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                this@MainActivity.showToast("再按一次退出应用")
                mExitTime = System.currentTimeMillis();
            } else {
                //后台运行 不结束程序 只是退出到后台
                val intent = Intent()
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                startActivity(intent);
                //System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出 结束程序
            }
            return true;
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
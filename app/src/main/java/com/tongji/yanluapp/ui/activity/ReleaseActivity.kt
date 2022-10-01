package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.base.BaseActivity1
import com.tongji.yanluapp.app.utils.initClose
import com.tongji.yanluapp.databinding.ActivityReleaseBinding
import com.tongji.yanluapp.viewmodel.ReleaseViewModel

class ReleaseActivity : BaseActivity1<ReleaseViewModel, ActivityReleaseBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.toolbar.initClose("发布动态") {
            it.setBackgroundColor(R.color.colorPrimary)
            finish()
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
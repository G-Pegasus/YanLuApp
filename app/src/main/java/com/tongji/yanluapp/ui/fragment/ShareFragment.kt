package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import com.tongji.yanluapp.databinding.FragmentShareBinding
import com.tongji.yanluapp.viewmodel.ShareViewModel
import com.tongji.yanluapp.app.base.BaseFragment1
import com.tongji.yanluapp.ui.activity.ReleaseActivity
import me.hgj.jetpackmvvm.util.startActivity

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:40
 * @description:
 * @email: tongji0x208@gmail.com
 */
class ShareFragment : BaseFragment1<ShareViewModel, FragmentShareBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.shareFloatBtn.setOnClickListener {
            startActivity<ReleaseActivity>()
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
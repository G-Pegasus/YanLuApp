package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tongji.yanluapp.utils.init
import com.tongji.yanluapp.databinding.FragmentHomeBinding
import com.tongji.yanluapp.viewmodel.HomeViewModel
import com.tongji.lib_base.ui.BaseFragment1

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:14
 * @description:
 * @email: tongji0x208@gmail.com
 */
class HomeFragment : BaseFragment1<HomeViewModel, FragmentHomeBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        val fragments = ArrayList<Fragment>()
        val tabTitles = arrayListOf("推荐", "分享")
        fragments.add(RecFragment())
        fragments.add(ShareFragment())
        mViewBind.vpHome.init(this, fragments)
        mViewBind.vpHome.offscreenPageLimit = 2
        TabLayoutMediator(mViewBind.tbHome, mViewBind.vpHome, true, true) {
                tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
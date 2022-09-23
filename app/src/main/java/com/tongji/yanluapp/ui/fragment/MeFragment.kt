package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.databinding.FragmentMeBinding
import com.tongji.yanluapp.ui.fragment.dialog.AboutAuthor
import com.tongji.yanluapp.ui.fragment.dialog.SetUserInfo
import com.tongji.yanluapp.viewmodel.MeViewModel
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment1

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:18
 * @description:
 * @email: tongji0x208@gmail.com
 */
class MeFragment : BaseFragment1<MeViewModel, FragmentMeBinding>() {

    private val mmkv: MMKV = MMKV.defaultMMKV()

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.tvUserName.text = mmkv.decodeString("userName")
        mViewBind.tvUserDes.text = mmkv.decodeString("userDes")

        mViewBind.rootAuthorInfo.setOnClickListener {
            AboutAuthor().show(childFragmentManager, "AuthorAbout")
        }

        mViewBind.rootSetInfo.setOnClickListener {
            SetUserInfo().show(childFragmentManager, "SetUserInfo")
        }

        mViewBind.tvRefresh.setOnClickListener {
            mViewBind.tvUserName.text = mmkv.decodeString("userName")
            mViewBind.tvUserDes.text = mmkv.decodeString("userDes")
        }

    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
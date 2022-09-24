package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.app.data.SchoolData
import com.tongji.yanluapp.databinding.FragmentMeBinding
import com.tongji.yanluapp.ui.activity.LikeActivity
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.fragment.dialog.AboutAuthor
import com.tongji.yanluapp.ui.fragment.dialog.RewardAuthor
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

        mViewBind.rootMoney.setOnClickListener {
            RewardAuthor().show(childFragmentManager, "RewardAuthor")
        }

        mViewBind.rootUserLike.setOnClickListener {
            val intent = Intent(requireContext(), LikeActivity::class.java)
            startActivity(intent)
        }

        mViewBind.btnStar.setOnClickListener {
            val intent = Intent(context, SchoolInfoActivity::class.java)
            val bundle = Bundle()
            val web = "https://github.com/G-Pegasus/YanLuApp"
            bundle.putString("data", web)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
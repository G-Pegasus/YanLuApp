package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.R
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.yanluapp.utils.GlideEngine
import com.tongji.yanluapp.utils.showToast
import com.tongji.yanluapp.databinding.FragmentMeBinding
import com.tongji.yanluapp.ui.activity.LikeActivity
import com.tongji.yanluapp.ui.activity.LoginActivity
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.fragment.dialog.AboutAuthor
import com.tongji.yanluapp.ui.fragment.dialog.RewardAuthor
import com.tongji.yanluapp.ui.fragment.dialog.SetUserInfo
import com.tongji.yanluapp.viewmodel.MeViewModel
import com.tongji.lib_base.ui.BaseFragment1
import com.tongji.lib_common.network.NetworkApi
import com.tongji.yanluapp.ui.activity.SelfPostActivity
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:18
 * @description:
 * @email: tongji0x208@gmail.com
 */
class MeFragment : BaseFragment1<MeViewModel, FragmentMeBinding>() {

    private val mmkv: MMKV = MMKV.defaultMMKV()

    override fun initView(savedInstanceState: Bundle?) {

        if (CacheUtil.isLogin()) {
            if (CacheUtil.getUser()?.user_head != "") {
                Glide.with(requireContext()).load(CacheUtil.getUser()?.user_head).into(mViewBind.ivUserPortrait)
            } else {
                Glide.with(requireContext()).load(R.mipmap.portrait).into(mViewBind.ivUserPortrait)
            }

            if (CacheUtil.getUserInfo()?.userName != "") {
                mViewBind.tvUserName.text = CacheUtil.getUserInfo()?.userName
            } else {
                mViewBind.tvUserName.text = "考研人"
            }

            if (CacheUtil.getUserInfo()?.userSign != "") {
                mViewBind.tvUserDes.text = CacheUtil.getUserInfo()?.userSign
            } else {
                mViewBind.tvUserDes.text = "加油！"
            }

            mViewBind.tvLogin.text = "退出登录"

        } else {
            Glide.with(requireContext()).load(R.mipmap.portrait).into(mViewBind.ivUserPortrait)
            mViewBind.tvUserName.text = "考研人"
            mViewBind.tvUserDes.text = "加油！"
        }

        // 跳转个人动态界面
        mViewBind.tvSelfPost.setOnClickListener {
            startActivity<SelfPostActivity>()
        }

        mViewBind.refreshLayout.setRefreshHeader(BezierRadarHeader(requireContext())
            .setEnableHorizontalDrag(true)
            .setPrimaryColorId(R.color.colorPrimary)
        ).setHeaderHeight(60F)

        mViewBind.refreshLayout.setOnRefreshListener {
            if (CacheUtil.getUserInfo()?.userName == null && CacheUtil.getUserInfo()?.userSign == null) {
                requireContext().showToast("未获取到更改信息")
            } else {
                mViewModel.updateInfo(
                    CacheUtil.getUserInfo()!!.userName,
                    CacheUtil.getUserInfo()!!.userSign
                )
                CacheUtil.getUser()?.user_name = CacheUtil.getUserInfo()!!.userName
                CacheUtil.getUser()?.user_sign = CacheUtil.getUserInfo()!!.userSign
                mViewBind.tvUserName.text = CacheUtil.getUserInfo()?.userName
                mViewBind.tvUserDes.text = CacheUtil.getUserInfo()?.userSign
            }

            if (!CacheUtil.isLogin()) {
                Glide.with(requireContext()).load(R.mipmap.portrait).into(mViewBind.ivUserPortrait)
                mViewBind.tvUserName.text = "考研人"
                mViewBind.tvUserDes.text = "加油！"
            }
            it.finishRefresh(800)
        }

        mViewBind.ivUserPortrait.setOnClickListener {
            PictureSelector.create(requireContext())
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setMaxSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {

                    override fun onResult(result: ArrayList<LocalMedia>) {

                        mViewModel.uploadAvatar(result[0])

                        mViewModel.avatarResult.observe(this@MeFragment) { resultState ->
                            parseState(resultState, {
                                Glide.with(requireContext()).load(it.headUrl).into(mViewBind.ivUserPortrait)
                                mmkv.encode("avatar", it.headUrl)
                            }, {
                                requireContext().showToast(it.errorMsg)
                            })
                        }
                    }

                    override fun onCancel() {
                        requireContext().showToast("未选中图片")
                    }
                })
        }

        mViewBind.rootLogin.setOnClickListener {
            if (!CacheUtil.isLogin()) {
                startActivity<LoginActivity>()
            } else {
                // EditLogin().show(childFragmentManager, "EditLogin")
                // CacheUtil.setIsLogin(false)
                NetworkApi.INSTANCE.cookieJar.clear()
                CacheUtil.setUser(null)
                mViewBind.refreshLayout.autoRefresh()
                mViewBind.tvLogin.text = "登录 / 注册"
            }
        }

        mViewBind.rootAuthorInfo.setOnClickListener {
            AboutAuthor().show(childFragmentManager, "AuthorAbout")
        }

        if (CacheUtil.isLogin()) {
            mViewBind.rootSetInfo.setOnClickListener {
                SetUserInfo().show(childFragmentManager, "SetUserInfo")
            }
        } else {
            requireContext().showToast("您需要先登录哦")
        }

        mViewModel.userInfo.observe(this@MeFragment) {
            mViewBind.tvUserName.text = it.userName
            mViewBind.tvUserDes.text = it.userSign
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
package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.app.network.response.UserInfoResponse
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.app.utils.GlideEngine
import com.tongji.yanluapp.app.utils.showToast
import com.tongji.yanluapp.databinding.FragmentMeBinding
import com.tongji.yanluapp.ui.activity.LikeActivity
import com.tongji.yanluapp.ui.activity.LoginActivity
import com.tongji.yanluapp.ui.activity.MainActivity
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.fragment.dialog.AboutAuthor
import com.tongji.yanluapp.ui.fragment.dialog.RewardAuthor
import com.tongji.yanluapp.ui.fragment.dialog.SetUserInfo
import com.tongji.yanluapp.viewmodel.MeViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment1
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity
import java.io.File


/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:18
 * @description:
 * @email: tongji0x208@gmail.com
 */
class MeFragment : BaseFragment1<MeViewModel, FragmentMeBinding>() {

    private val mmkv: MMKV = MMKV.defaultMMKV()

    override fun initView(savedInstanceState: Bundle?) {

        // mViewBind.tvUserName.text = mmkv.decodeString("userName")
        // mViewBind.tvUserDes.text = mmkv.decodeString("userDes")

        if (CacheUtil.isLogin()) {
            Glide.with(requireContext()).load(mmkv.decodeString("avatar")).into(mViewBind.ivUserPortrait)
            mViewBind.tvUserName.text = CacheUtil.getUser()?.userName
            mViewBind.tvUserDes.text = CacheUtil.getUser()?.userSign
        } else {
            mViewBind.tvUserName.text = "考研人"
            mViewBind.tvUserDes.text = "加油！"
        }

        mViewBind.ivUserPortrait.setOnClickListener {
            PictureSelector.create(requireContext())
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(GlideEngine.createGlideEngine())
                .setMaxSelectNum(1)
                .forResult(object : OnResultCallbackListener<LocalMedia> {

                    override fun onResult(result: ArrayList<LocalMedia>) {

                        mViewModel.uploadAvatar(File(result[0].realPath))

                        mViewModel.avatarResult.observe(this@MeFragment) { resultState ->
                            parseState(resultState, {
                                Glide.with(requireContext()).load(result[0].realPath).into(mViewBind.ivUserPortrait)
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
                Toast.makeText(appContext, "您已经登录过了呦~", Toast.LENGTH_SHORT).show()
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

        mViewModel.updateInfoResult.observe(this@MeFragment) { resultState ->
            parseState(resultState, {
                mViewBind.tvUserName.text = it.userName
                mViewBind.tvUserDes.text = it.userSign
                CacheUtil.setUser(
                    UserInfoResponse(
                        it.userName,
                        CacheUtil.getUser()!!.userNumber,
                        CacheUtil.getUser()!!.token,
                        CacheUtil.getUser()!!.avatar,
                        it.userSign
                    )
                )
            }, {
                // 更新失败
                Toast.makeText(appContext, it.errorMsg, Toast.LENGTH_SHORT).show()
            })
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
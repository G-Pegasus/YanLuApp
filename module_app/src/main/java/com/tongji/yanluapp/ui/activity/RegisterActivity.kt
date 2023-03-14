package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.yanluapp.utils.showToast
import com.tongji.yanluapp.databinding.ActivityRegisterBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import com.tongji.lib_base.ui.BaseActivity1
import com.tongji.lib_common.bean.UpdateInfoResponse
import me.hgj.jetpackmvvm.ext.parseState

class RegisterActivity : BaseActivity1<LoginViewModel, ActivityRegisterBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        // 注册
        mViewBind.btnRegister.setOnClickListener {
            when {
                mViewBind.registerUsername.text.toString().isEmpty() -> this.showToast("请填写账号")
                mViewBind.registerNickname.text.toString().isEmpty() -> this.showToast("请填写昵称")
                mViewBind.registerPwd.text.toString().isEmpty() -> this.showToast("请填写密码")
                mViewBind.registerPwdTwo.text.toString().isEmpty() -> this.showToast("请再次填写密码")
                mViewBind.registerPwd.text.toString().length < 6 -> this.showToast("密码最少6位")
                mViewBind.registerPwd.text.toString() != mViewBind.registerPwdTwo.text.toString() -> this.showToast("密码不一致")
                else -> mViewModel.registerAndLogin(
                    mViewBind.registerUsername.text.toString(),
                    mViewBind.registerNickname.text.toString(),
                    mViewBind.registerPwdTwo.text.toString(),
                )
            }
        }

        mViewModel.loginResult.observe(this@RegisterActivity) { resultState ->
            parseState(resultState, {
                startActivity(Intent(this,MainActivity::class.java))
                CacheUtil.setIsLogin(true)
                CacheUtil.setUser(it)
                if (it.user_name != "" && it.user_sign != "") {
                    CacheUtil.setUserInfo(UpdateInfoResponse(it.user_name, it.user_sign))
                } else {
                    CacheUtil.setUserInfo(UpdateInfoResponse("考研人", "加油"))
                }
                finish()
            }, {
                this.showToast(it.errorMsg)
            })
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
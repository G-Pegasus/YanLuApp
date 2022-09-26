package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.app.utils.showToast
import com.tongji.yanluapp.databinding.ActivityRegisterBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import me.hgj.jetpackmvvm.demo.app.base.BaseActivity1
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity

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
                startActivity<MainActivity>()
                CacheUtil.setIsLogin(true)
                CacheUtil.setUser(it)
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
package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.tongji.yanluapp.app.network.response.UpdateInfoResponse
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.databinding.ActivityLoginBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import me.hgj.jetpackmvvm.base.appContext
import com.tongji.yanluapp.app.base.BaseActivity1
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity

class LoginActivity : BaseActivity1<LoginViewModel, ActivityLoginBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.btnLogin.setOnClickListener {
            when {
                mViewBind.etUsername.text.toString().isEmpty() ->
                    Toast.makeText(appContext, "请填写账号", Toast.LENGTH_SHORT).show()
                mViewBind.etPwd.text.toString().isEmpty() ->
                    Toast.makeText(appContext, "请填写密码", Toast.LENGTH_SHORT).show()
                else -> mViewModel.loginReq(
                    mViewBind.etUsername.text.toString(),
                    mViewBind.etPwd.text.toString()
                )
            }
        }

        mViewBind.toRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }

        mViewModel.loginResult.observe(this@LoginActivity) { resultState ->
            parseState(resultState, {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
                CacheUtil.setUser(it)
                CacheUtil.setUserInfo(UpdateInfoResponse(it.user_name, it.user_sign))
                CacheUtil.setIsLogin(true)
                // finish()
            }, {
                //登录失败
                Toast.makeText(appContext, it.errorMsg, Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
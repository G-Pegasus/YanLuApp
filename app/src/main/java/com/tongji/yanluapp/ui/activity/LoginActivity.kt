package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.databinding.ActivityLoginBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.demo.app.base.BaseActivity1
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
                startActivity<MainActivity>()
                CacheUtil.setUser(it)
                CacheUtil.setIsLogin(true)
                finish()
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
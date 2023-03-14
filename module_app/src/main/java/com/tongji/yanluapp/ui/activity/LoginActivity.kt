package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.tongji.lib_common.bean.UpdateInfoResponse
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.yanluapp.databinding.ActivityLoginBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import me.hgj.jetpackmvvm.base.appContext
import com.tongji.lib_base.ui.BaseActivity1
import me.hgj.jetpackmvvm.ext.parseState

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
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        mViewModel.loginResult.observe(this@LoginActivity) { resultState ->
            parseState(resultState, {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
                CacheUtil.setUser(it)
                if (it.user_name != "" && it.user_sign != "") {
                    CacheUtil.setUserInfo(UpdateInfoResponse(it.user_name, it.user_sign))
                } else {
                    CacheUtil.setUserInfo(UpdateInfoResponse("考研人", "加油"))
                }

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
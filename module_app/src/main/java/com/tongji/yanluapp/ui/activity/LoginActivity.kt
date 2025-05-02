package com.tongji.yanluapp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.luck.picture.lib.utils.ToastUtils
import com.tongji.lib_base.ui.BaseActivity1
import com.tongji.lib_common.bean.UpdateInfoResponse
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.lib_common.utils.JsonUtils.createJsonRequestBody
import com.tongji.yanluapp.databinding.ActivityLoginBinding
import com.tongji.yanluapp.viewmodel.LoginViewModel
import me.hgj.jetpackmvvm.ext.parseState
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class LoginActivity : BaseActivity1<LoginViewModel, ActivityLoginBinding>() {

    override fun initView(savedInstanceState: Bundle?) {

        mViewBind.btnLogin.setOnClickListener {
//            when {
//                mViewBind.etUsername.text.toString().isEmpty() ->
//                    ToastUtils.showToast(this@LoginActivity, "请输入账号")
//                mViewBind.etPwd.text.toString().isEmpty() ->
//                    ToastUtils.showToast(this@LoginActivity, "请填写密码")
//                else -> mViewModel.loginReq(
//                    mViewBind.etUsername.text.toString(),
//                    mViewBind.etPwd.text.toString()
//                )
//            }
//            val body: RequestBody = "{\"phone\":\"${mViewBind.etUsername.text}\"}"
//                .toRequestBody("application/json".toMediaTypeOrNull())

            val requestData = mapOf("phone" to mViewBind.etUsername.text.toString())
            val body = createJsonRequestBody(requestData)

            mViewModel.sendSysCode(body)
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
                ToastUtils.showToast(this@LoginActivity, it.errorMsg)
            })
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
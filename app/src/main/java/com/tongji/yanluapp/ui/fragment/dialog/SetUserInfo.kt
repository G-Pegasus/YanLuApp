package com.tongji.yanluapp.ui.fragment.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.network.response.UpdateInfoResponse
import com.tongji.yanluapp.app.network.response.UserInfoResponse
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.viewmodel.MeViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @Author Tongji
 * @Description
 * @Date create in 2022/9/23 21:03
 */
class SetUserInfo : DialogFragment() {

    // private val mmkv: MMKV = MMKV.defaultMMKV()
    // private val viewModel by lazy { ViewModelProvider(this)[MeViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_setting, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val getName: EditText = view.findViewById(R.id.input_user_name)
        val getDes: EditText = view.findViewById(R.id.input_user_des)
        val tvCancel: TextView = view.findViewById(R.id.tv_cancel_set)
        val tvSure: TextView = view.findViewById(R.id.tv_sure_set)

        tvSure.setOnClickListener {
            val userName = getName.text.toString()
            val userDes = getDes.text.toString()
            // mmkv.encode("userName", userName)
            // mmkv.encode("userDes", userDes)
            when {
                userName.isEmpty() ->
                    Toast.makeText(appContext, "请填写昵称", Toast.LENGTH_SHORT).show()
                userDes.isEmpty() ->
                    Toast.makeText(appContext, "请填写签名", Toast.LENGTH_SHORT).show()
                else -> {
                    CacheUtil.setUserInfo(UpdateInfoResponse(userName, userDes))
                }
            }
            dismiss()
        }

        tvCancel.setOnClickListener {
            dismiss()
        }

    }
}
package com.tongji.yanluapp.ui.fragment.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tongji.yanluapp.R
import com.tongji.lib_common.bean.UpdateInfoResponse
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.yanluapp.utils.showToast
import me.hgj.jetpackmvvm.base.appContext

/**
 * @Author Tongji
 * @Description
 * @Date create in 2022/9/23 21:03
 */
class SetUserInfo : DialogFragment() {

    // private val mmkv: MMKV = MMKV.defaultMMKV()
    // private val viewModel by lazy { ViewModelProvider(this)[MeViewModel::class.java] }
    private val imm: InputMethodManager? by lazy { activity?.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager? }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_setting, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val getName: EditText = view.findViewById(R.id.input_user_name)
        val getDes: EditText = view.findViewById(R.id.input_user_des)
        val tvCancel: TextView = view.findViewById(R.id.tv_cancel_set)
        val tvSure: TextView = view.findViewById(R.id.tv_sure_set)

        imm?.let {
            getName.requestFocus()
            it.showSoftInput(getName, InputMethodManager.SHOW_FORCED)
        }

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
                    requireContext().showToast("修改完成，记得上拉刷新哦")
                }
            }
            imm?.hideSoftInputFromWindow(getDes.windowToken, 0)
            dismissAllowingStateLoss()
        }

        tvCancel.setOnClickListener {
            imm?.hideSoftInputFromWindow(getDes.windowToken, 0)
            dismissAllowingStateLoss()
        }

    }
}
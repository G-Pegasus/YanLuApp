package com.tongji.yanluapp.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.R
/**
 * @Author Tongji
 * @Description
 * @Date create in 2022/9/23 21:03
 */
class SetUserInfo : DialogFragment() {

    private val mmkv: MMKV = MMKV.defaultMMKV()

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
            mmkv.encode("userName", userName)
            mmkv.encode("userDes", userDes)
            dismiss()
        }

        tvCancel.setOnClickListener {
            dismiss()
        }

    }
}
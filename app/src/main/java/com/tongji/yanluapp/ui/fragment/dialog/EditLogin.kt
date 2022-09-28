package com.tongji.yanluapp.ui.fragment.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.utils.CacheUtil

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/28 20:41
 * @description:
 * @email: tongji0x208@gmail.com
 */
class EditLogin : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_tip, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvCancel: TextView = view.findViewById(R.id.tv_cancel_set)
        val tvSure: TextView = view.findViewById(R.id.tv_sure_set)

        tvSure.setOnClickListener {
            CacheUtil.setIsLogin(false)
            dismissAllowingStateLoss()
        }

        tvCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }

    }
}
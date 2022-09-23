package com.tongji.yanluapp.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tongji.yanluapp.R

/**
 * @Author Tongji
 * @Description 关于作者的弹窗
 * @Date create in 2022/7/23 13:30
 */
class AboutAuthor : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_author, container)
    }

}
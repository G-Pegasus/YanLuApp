package com.tongji.yanluapp.ui.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tongji.yanluapp.R

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/23 21:11
 * @description:
 * @email: tongji0x208@gmail.com
 */
class RewardAuthor : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_money, container)
    }

}
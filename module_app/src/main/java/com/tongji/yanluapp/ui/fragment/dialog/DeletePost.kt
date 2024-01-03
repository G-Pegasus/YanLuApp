package com.tongji.yanluapp.ui.fragment.dialog

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.tongji.yanluapp.R
import com.tongji.yanluapp.viewmodel.DialogViewModel

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/28 20:41
 * @description:
 * @email: tongji0x208@gmail.com
 */
class DeletePost : DialogFragment() {

    private val viewModel by lazy { ViewModelProvider(this)[DialogViewModel::class.java] }
    private lateinit var mOnItemClickListener: OnItemClickListener

    fun newInstance(tittle: String): DeletePost {
        val fragment = DeletePost()
        val bundle = Bundle()
        bundle.putString("postId", tittle)
        fragment.arguments = bundle
        return fragment
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_delete_post, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvCancel: TextView = view.findViewById(R.id.tv_cancel_set)
        val tvSure: TextView = view.findViewById(R.id.tv_sure_set)

        tvSure.setOnClickListener {
            mOnItemClickListener.onItemClick()
            val postId = requireArguments().getString("postId")
            viewModel.deletePost(postId!!)
            dismissAllowingStateLoss()
        }

        tvCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }

    }

    interface OnItemClickListener {
        fun onItemClick()
    }
}
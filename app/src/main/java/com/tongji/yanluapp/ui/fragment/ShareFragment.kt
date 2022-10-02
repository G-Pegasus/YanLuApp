package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.tongji.yanluapp.R
import com.tongji.yanluapp.databinding.FragmentShareBinding
import com.tongji.yanluapp.viewmodel.ShareViewModel
import com.tongji.yanluapp.app.base.BaseFragment1
import com.tongji.yanluapp.ui.activity.ReleaseActivity
import com.tongji.yanluapp.ui.adapter.PostAdapter
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.startActivity

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:40
 * @description:
 * @email: tongji0x208@gmail.com
 */
class ShareFragment : BaseFragment1<ShareViewModel, FragmentShareBinding>() {

    private lateinit var postAdapter: PostAdapter

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.shareFloatBtn.setOnClickListener {
            startActivity<ReleaseActivity>()
        }

        val rvPost = mViewBind.rvShare
        rvPost.layoutManager = LinearLayoutManager(appContext)

        mViewModel.getPost()
        mViewModel.postResult.observe(viewLifecycleOwner) { resultState ->
            parseState(resultState, {
                postAdapter = PostAdapter(it)
                rvPost.adapter = postAdapter
            })
        }

        mViewBind.shareRefresh.setRefreshHeader(
            BezierRadarHeader(requireContext())
            .setEnableHorizontalDrag(true)
            .setPrimaryColorId(R.color.colorPrimary)
        ).setHeaderHeight(60F)

        mViewBind.shareRefresh.setOnRefreshListener {
            mViewModel.getPost()
            postAdapter.notifyDataSetChanged()
            it.finishRefresh(1000)
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.header.MaterialHeader
import com.tongji.yanluapp.R
import com.tongji.lib_base.ui.BaseActivity1
import com.tongji.yanluapp.utils.initClose
import com.tongji.yanluapp.databinding.ActivitySelfPostBinding
import com.tongji.yanluapp.ui.adapter.SelfPostAdapter
import com.tongji.yanluapp.viewmodel.SelfPostViewModel
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.parseState

class SelfPostActivity : BaseActivity1<SelfPostViewModel, ActivitySelfPostBinding>() {

    private lateinit var selfPostAdapter: SelfPostAdapter

    override fun initView(savedInstanceState: Bundle?) {
        val rvSelfPost = mViewBind.rvSelfPost
        rvSelfPost.layoutManager = LinearLayoutManager(appContext)

        mViewBind.selfToolbar.initClose("我的帖子") {
            it.setBackgroundColor(R.color.colorPrimary)
            finish()
        }

        mViewModel.getSelfPost()
        mViewModel.selfPostResult.observe(this@SelfPostActivity) { resultState ->
            parseState(resultState, {
                selfPostAdapter = SelfPostAdapter(this@SelfPostActivity, supportFragmentManager, it)
                rvSelfPost.adapter = selfPostAdapter

                selfPostAdapter.setOnItemClickListener(object : SelfPostAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        mViewModel.postLike(it[position].post_id)
                    }

                    override fun onItemLongClick(view: View, position: Int) {}
                })
            })
        }

        mViewBind.selfRefresh.setRefreshHeader(
//            BezierRadarHeader(requireContext())
//            .setEnableHorizontalDrag(true)
//            .setPrimaryColorId(R.color.colorPrimary)
            MaterialHeader(this@SelfPostActivity).setShowBezierWave(true)
        ).setHeaderHeight(60F).setPrimaryColorsId(R.color.colorPrimary)

        mViewBind.selfRefresh.setOnRefreshListener {
            mViewModel.getSelfPost()
            selfPostAdapter.notifyDataSetChanged()
            it.finishRefresh(800)
        }
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
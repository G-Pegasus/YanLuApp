package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.header.MaterialHeader
import com.tongji.yanluapp.R
import com.tongji.yanluapp.databinding.FragmentShareBinding
import com.tongji.yanluapp.viewmodel.ShareViewModel
import com.tongji.lib_base.ui.BaseFragment1
import com.tongji.lib_common.utils.CacheUtil
import com.tongji.yanluapp.ui.activity.ReleaseActivity
import com.tongji.yanluapp.ui.adapter.PostAdapter
import com.tongji.yanluapp.utils.showToast
import kotlinx.coroutines.delay
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

        if (CacheUtil.isLogin()) {
            mViewModel.getPost()
        }

        rvPost.itemAnimator?.changeDuration = 0
        mViewModel.postResult.observe(viewLifecycleOwner) { resultState ->
            parseState(resultState, {
                postAdapter = PostAdapter(requireContext(), it)
                rvPost.adapter = postAdapter

                postAdapter.setOnItemClickListener(object : PostAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        mViewModel.postLike(it[position].post_id)
                    }

                    override fun onItemLongClick(view: View, position: Int) {}
                })
            })
        }

        mViewBind.shareRefresh.setRefreshHeader(
//            BezierRadarHeader(requireContext())
//            .setEnableHorizontalDrag(true)
//            .setPrimaryColorId(R.color.colorPrimary)
            MaterialHeader(requireContext()).setShowBezierWave(true)
        ).setHeaderHeight(60F).setPrimaryColorsId(R.color.colorPrimary)

        mViewBind.shareRefresh.setOnRefreshListener {
            mViewModel.getPost()
            postAdapter.notifyDataSetChanged()
            it.finishRefresh(800)
        }
    }

    override fun dismissLoading() { }

    override fun showLoading(message: String) { }

}

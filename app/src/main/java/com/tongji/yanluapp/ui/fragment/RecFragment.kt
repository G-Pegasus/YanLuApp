package com.tongji.yanluapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.header.MaterialHeader
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.utils.showToast
import com.tongji.yanluapp.databinding.FragmentRecBinding
import com.tongji.yanluapp.ui.adapter.BannerAdapter
import com.tongji.yanluapp.viewmodel.RecViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorStyle
import com.tongji.yanluapp.app.base.BaseFragment1
import com.tongji.yanluapp.app.utils.CacheUtil
import com.tongji.yanluapp.ui.activity.SchoolInfoActivity
import com.tongji.yanluapp.ui.adapter.ArticleAdapter
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.parseState
import java.util.*

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:37
 * @description:
 * @email: tongji0x208@gmail.com
 */
class RecFragment : BaseFragment1<RecViewModel, FragmentRecBinding>() {

    private lateinit var bannerViewPager: BannerViewPager<String>
    private lateinit var articleAdapter: ArticleAdapter

    override fun initView(savedInstanceState: Bundle?) {
        if (CacheUtil.isLogin()) {
            mViewModel.getBannerImage()
        }

        bannerViewPager = view!!.findViewById(R.id.banner)
        bannerViewPager.adapter = BannerAdapter(requireContext())
        bannerViewPager.setLifecycleRegistry(lifecycle)
        bannerViewPager.setIndicatorStyle(IndicatorStyle.DASH)
        bannerViewPager.setPageStyle(PageStyle.MULTI_PAGE_SCALE)
        bannerViewPager.setInterval(2800)
        mViewModel.bannerResult.observe(viewLifecycleOwner) { resultState ->
            parseState(resultState, {
                bannerViewPager.create(it.fileList)
            }, {
                requireContext().showToast(it.errorMsg)
            })
        }

        val rvArticle = mViewBind.rvRec
        rvArticle.layoutManager = LinearLayoutManager(appContext)

        if (CacheUtil.isLogin()) {
            mViewModel.getArticles()
        }
        mViewModel.articleResult.observe(viewLifecycleOwner) { resultState ->
            parseState(resultState, {
                it.shuffle()
                articleAdapter = ArticleAdapter(requireContext(), it)
                rvArticle.adapter = articleAdapter
                rvArticle.layoutAnimation =
                    LayoutAnimationController(AnimationUtils.loadAnimation(appContext, R.anim.animation))

                articleAdapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(context, SchoolInfoActivity::class.java)
                        val bundle = Bundle()
                        val articleWeb = it[position].recommend_url
                        bundle.putString("data", articleWeb)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                })
            })
        }

        mViewBind.recRefresh.setRefreshHeader(
            MaterialHeader(requireContext()).setShowBezierWave(true)
        ).setHeaderHeight(60F).setPrimaryColorsId(R.color.colorPrimary)

        mViewBind.recRefresh.setOnRefreshListener {
            if (CacheUtil.isLogin()) {
                mViewModel.getArticles()
                articleAdapter.notifyDataSetChanged()
                it.finishRefresh(800)
            } else {
                requireContext().showToast("请登录以加载更多数据")
                it.finishRefresh(800)
            }
        }

    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
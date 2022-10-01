package com.tongji.yanluapp.ui.fragment

import android.os.Bundle
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.utils.showToast
import com.tongji.yanluapp.databinding.FragmentRecBinding
import com.tongji.yanluapp.ui.adapter.BannerAdapter
import com.tongji.yanluapp.viewmodel.RecViewModel
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorStyle
import com.tongji.yanluapp.app.base.BaseFragment1
import me.hgj.jetpackmvvm.ext.parseState

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 13:37
 * @description:
 * @email: tongji0x208@gmail.com
 */
class RecFragment : BaseFragment1<RecViewModel, FragmentRecBinding>() {

    private lateinit var bannerViewPager: BannerViewPager<String>

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.getBannerImage()

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

    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
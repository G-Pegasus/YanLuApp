package com.tongji.yanluapp.ui.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.network.response.BannerImageResponse
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/29 15:24
 * @description:
 * @email: tongji0x208@gmail.com
 */
class BannerAdapter(val context: Context) : BaseBannerAdapter<String>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner
    }

    override fun bindData(
        holder: BaseViewHolder<String>,
        data: String,
        position: Int,
        pageSize: Int
    ) {
        Glide.with(context).load(data).into(holder.findViewById(R.id.iv_banner))
    }


}
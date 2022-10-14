package com.tongji.yanluapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType.ofImage
import com.luck.picture.lib.config.SelectModeConfig.MULTIPLE
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.tongji.yanluapp.R
import com.tongji.lib_base.ui.BaseActivity1
import com.tongji.yanluapp.utils.GlideEngine
import com.tongji.yanluapp.utils.initClose
import com.tongji.yanluapp.utils.showToast
import com.tongji.yanluapp.databinding.ActivityReleaseBinding
import com.tongji.yanluapp.ui.adapter.ImageAdapter
import com.tongji.yanluapp.viewmodel.ReleaseViewModel
import me.hgj.jetpackmvvm.base.appContext
import java.util.ArrayList


class ReleaseActivity : BaseActivity1<ReleaseViewModel, ActivityReleaseBinding>() {

    private var mediaList = mutableListOf<LocalMedia>()
    private lateinit var imageAdapter: ImageAdapter

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.toolbar.initClose("发布动态") {
            it.setBackgroundColor(R.color.colorPrimary)
            finish()
        }

        val rvImage = mViewBind.rvImage
        rvImage.layoutManager = GridLayoutManager(appContext, 3)
        imageAdapter = ImageAdapter(this@ReleaseActivity, 3)
        rvImage.adapter = imageAdapter
        imageAdapter.setOnItemClickListener(object : ImageAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                if (mediaList.size > 0) {
                    val media = mediaList[position]
                    PictureSelector.create(this@ReleaseActivity).openPreview()
                }
            }
        })

        imageAdapter.setOnAddClickListener(object : ImageAdapter.OnAddClickListener {
            override fun onAddClick() {
                showAlbum()
            }
        })

        mViewBind.btnRelease.setOnClickListener {
            if (mViewBind.etShare.text.toString().isEmpty()) {
                this@ReleaseActivity.showToast("请填写分享内容")
            } else {
                Log.e("tongji", mediaList.size.toString())
                mViewModel.uploadImages(mViewBind.etShare.text.toString(), mediaList)
                this@ReleaseActivity.showToast("发布成功！")
                finish()
            }
        }
    }

    private fun showAlbum() {
        //参数很多，根据需要添加
        PictureSelector.create(this@ReleaseActivity)
            .openGallery(ofImage())
            .setMaxSelectNum(3)
            .setMinSelectNum(1)
            .setImageEngine(GlideEngine.createGlideEngine())
            .setSelectionMode(MULTIPLE) // 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
            .isPreviewImage(true)
            .forResult(object : OnResultCallbackListener<LocalMedia> {
                override fun onResult(result: ArrayList<LocalMedia>) {
                    mediaList.addAll(result)
                    imageAdapter.setList(mediaList)
                    imageAdapter.notifyDataSetChanged()
                }

                override fun onCancel() {
                    this@ReleaseActivity.showToast("未选中图片")
                }
            })
    }

    override fun dismissLoading() {

    }

    override fun showLoading(message: String) {

    }

}
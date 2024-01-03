package com.tongji.yanluapp.ui.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cc.shinichi.library.ImagePreview
import cc.shinichi.library.view.listener.OnBigImageClickListener
import cc.shinichi.library.view.listener.OnBigImageLongClickListener
import cc.shinichi.library.view.listener.OnDownloadListener
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.luck.picture.lib.utils.ToastUtils
import com.tongji.yanluapp.R
import me.hgj.jetpackmvvm.ext.download.OnDownLoadListener

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 16:23
 * @description:
 * @email: tongji0x208@gmail.com
 */
class PostImageAdapter(private val context: Context, private val postImageList: ArrayList<String>)
    : RecyclerView.Adapter<PostImageAdapter.PostImageViewHolder>() {

    // 图片显示默认状态，手动原图
    private val loadStrategy: ImagePreview.LoadStrategy = ImagePreview.LoadStrategy.Default

    inner class PostImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImagePost : ShapeableImageView = itemView.findViewById(R.id.iv_image_post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_post, parent, false)
        return PostImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostImageViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(postImageList[position]).into(holder.ivImagePost)

        holder.ivImagePost.setOnClickListener {
//            val intent = Intent(context, PhotoShowActivity::class.java)
//            val bundle = Bundle()
//            val photoList = postImageList
//            bundle.putStringArrayList("data", photoList)
//            intent.putExtras(bundle)
//            context.startActivity(intent)

            ImagePreview.instance
                .setContext(context)
                .setIndex(position)
                .setImageList(postImageList)
                .setLoadStrategy(loadStrategy)
                 // 长图的展示模式，默认是SCALE_TYPE_CENTER_INSIDE，缩小到内部居中
                .setLongPicDisplayMode(ImagePreview.LongPicDisplayMode.FillWidth)
                .setFolderName("研路相随")
                .setZoomTransitionDuration(300)
                .setEnableClickClose(true) // 是否点击图片关闭
                .setEnableDragClose(true) // 是否下拉图片关闭
                .setEnableUpDragClose(false) // 是否上拉图片关闭
                .setShowCloseButton(false) // 是否显示图片关闭按钮 左下角
                .setShowDownButton(true) // 是否显示图片下载按钮 右下角
                //.setDownIconResId() // 设置下载按钮图片资源
                .setShowIndicator(true) // 设置顶部指示器 显示这是第几张图片
                //.setErrorPlaceHolder() // 设置失败时的占位图
                .setBigImageClickListener(object: OnBigImageClickListener {
                    override fun onClick(activity: Activity?, view: View?, position: Int) {}
                })
                .setBigImageLongClickListener(object: OnBigImageLongClickListener {
                    override fun onLongClick(
                        activity: Activity?,
                        view: View?,
                        position: Int
                    ): Boolean {
                        val dialog: AlertDialog = AlertDialog.Builder(activity)
                            .setTitle("提示")
                            .setMessage("是否下载该图片？")
                            .setPositiveButton("确定", null)
                            .setNegativeButton("取消", null)
                            .create()
                        dialog.show()
                        return true
                    }
                })
                //.setBigImagePageChangeListener() // 页面切换回调
                //.setDownloadClickListener() // 下载按钮点击回调，可以拦截下载逻辑，从而实现自己下载或埋点统计
                .setDownloadListener(object: OnDownloadListener() {
                    override fun onDownloadFailed(activity: Activity?, position: Int) {
                        ToastUtils.showToast(context, "下载失败，请重新尝试")
                    }

                    override fun onDownloadStart(activity: Activity?, position: Int) {}

                    override fun onDownloadSuccess(activity: Activity?, position: Int) {
                        ToastUtils.showToast(context, "已保存到相册")
                    }
                })
                //.setOnPageFinishListener() // 页面关闭回调
                //.setProgressLayoutId() // 设置查看原图时的百分比样式
                //.setPreviewLayoutResId() // 完全自定义预览界面
                .start()
        }
    }

    override fun getItemCount(): Int = postImageList.size
}

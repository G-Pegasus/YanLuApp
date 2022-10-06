package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luck.picture.lib.entity.LocalMedia
import com.tongji.yanluapp.R
import com.tongji.yanluapp.ui.activity.PhotoShowActivity
import me.hgj.jetpackmvvm.ext.view.invisible
import me.hgj.jetpackmvvm.ext.view.visible

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/1 23:22
 * @description:
 * @email: tongji0x208@gmail.com
 */

class ImageAdapter(
    private val context: Context,
    private val maxSize: Int = 3
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var mOnAddPicClickListener: OnAddClickListener
    private var mList =  mutableListOf<LocalMedia>()

    companion object {
        private const val TYPE_CAMERA = 1
        private const val TYPE_PICTURE = 2
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val delete: ImageView = itemView.findViewById(R.id.iv_delete)
        val image: ImageView = itemView.findViewById(R.id.fiv)
    }

    fun setList(list: MutableList<LocalMedia>) {
        this.mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_CAMERA) {
            holder.image.setImageResource(R.mipmap.release_image)
            holder.delete.invisible()
            holder.image.setOnClickListener {
                mOnAddPicClickListener.onAddClick()
            }
        } else {
            holder.delete.visible()
            holder.delete.setOnClickListener {
                val index = holder.bindingAdapterPosition
                if (index != RecyclerView.NO_POSITION) {
                    mList.removeAt(index)
                    notifyDataSetChanged()
                }
            }

            val media = mList[position]
            Glide.with(holder.itemView.context).load(media.realPath).into(holder.image)
            holder.image.setOnClickListener {
                val intent = Intent(context, PhotoShowActivity::class.java)
                val bundle = Bundle()
                val photoList = mList
                val photoPathList = ArrayList<String>()
                photoList.forEach {
                    photoPathList.add(it.realPath)
                }
                bundle.putStringArrayList("data", photoPathList)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    fun setOnAddClickListener(listener: OnAddClickListener) {
        this.mOnAddPicClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnAddClickListener {
        fun onAddClick()
    }

    override fun getItemViewType(position: Int): Int =
        if (isShowAddItem(position)) {
            TYPE_CAMERA
        } else {
            TYPE_PICTURE
        }

    private fun isShowAddItem(position: Int): Boolean = position == mList.size

    override fun getItemCount(): Int =
        if (mList.size < maxSize) {
            mList.size + 1
        } else {
            mList.size
        }
}
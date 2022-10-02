package com.tongji.yanluapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tongji.yanluapp.R

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 16:23
 * @description:
 * @email: tongji0x208@gmail.com
 */
class PostImageAdapter(private val postImageList: List<String>)
    : RecyclerView.Adapter<PostImageAdapter.PostImageViewHolder>() {

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
    }

    override fun getItemCount(): Int = postImageList.size
}
package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.App.Companion.context
import com.tongji.yanluapp.ui.activity.PhotoShowActivity
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 16:23
 * @description:
 * @email: tongji0x208@gmail.com
 */
class PostImageAdapter(private val context: Context, private val postImageList: ArrayList<String>)
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
        holder.ivImagePost.setOnClickListener {
            val intent = Intent(context, PhotoShowActivity::class.java)
            val bundle = Bundle()
            val photoList = postImageList
            bundle.putStringArrayList("data", photoList)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = postImageList.size
}
package com.tongji.yanluapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.network.response.PostData
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 14:31
 * @description:
 * @email: tongji0x208@gmail.com
 */
class PostAdapter(private val postList: List<PostData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CONTENT = 1
        private const val TYPE_IMAGE = 2
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_post_user_name)
        val tvPostTime: TextView = itemView.findViewById(R.id.tv_post_time)
        val tvPostContent: TextView = itemView.findViewById(R.id.tv_post_content)
        val tvPostLikeNum: TextView = itemView.findViewById(R.id.tv_like_num)
        val tvPostCommentNum: TextView = itemView.findViewById(R.id.tv_comment_num)
    }

    inner class PostImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar_1)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_post_user_name_1)
        val tvPostTime: TextView = itemView.findViewById(R.id.tv_post_time_1)
        val tvPostContent: TextView = itemView.findViewById(R.id.tv_post_content_1)
        val tvPostLikeNum: TextView = itemView.findViewById(R.id.tv_like_num_1)
        val tvPostCommentNum: TextView = itemView.findViewById(R.id.tv_comment_num_1)
        val rvPostImage: RecyclerView = itemView.findViewById(R.id.rv_post_image)
    }

    override fun getItemViewType(position: Int): Int = if (postList[position].post_image.isEmpty()) {
        TYPE_CONTENT
    } else {
        TYPE_IMAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_CONTENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post, parent, false)
                PostViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post_image, parent, false)
                PostImageViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_CONTENT -> {
                val contentHolder = holder as PostAdapter.PostViewHolder
                Glide.with(holder.itemView.context).load(postList[position].user_head).into(contentHolder.ivAvatar)
                contentHolder.tvUserName.text = postList[position].user_name
                contentHolder.tvPostTime.text = postList[position].post_last_time
                contentHolder.tvPostContent.text = postList[position].post_content.replace("\"", "")
                contentHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                contentHolder.tvPostCommentNum.text = "18"
            }

            else -> {
                val imageHolder = holder as PostAdapter.PostImageViewHolder
                Glide.with(holder.itemView.context).load(postList[position].user_head).into(imageHolder.ivAvatar)
                imageHolder.tvUserName.text = postList[position].user_name
                imageHolder.tvPostTime.text = postList[position].post_last_time
                imageHolder.tvPostContent.text = postList[position].post_content.replace("\"", "")
                imageHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                imageHolder.tvPostCommentNum.text = "18"

                val rvImage = imageHolder.rvPostImage
                val imageAdapter = PostImageAdapter(postList[position].post_image)
                rvImage.layoutManager = GridLayoutManager(appContext, 3)
                rvImage.adapter = imageAdapter
            }
        }
    }

    override fun getItemCount(): Int = postList.size
}
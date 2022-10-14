package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tongji.yanluapp.R
import com.tongji.lib_common.bean.PostData
import com.tongji.yanluapp.ui.fragment.dialog.DeletePost
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/6 10:42
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SelfPostAdapter(private val context: Context,
                      private val fragmentManager: FragmentManager,
                      private val postList: ArrayList<PostData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CONTENT = 1
        private const val TYPE_IMAGE = 2
    }

    private lateinit var onItemClickListener: OnItemClickListener

    inner class SelfPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar_self)
        val ivPostLike: ShapeableImageView = itemView.findViewById(R.id.iv_post_like_self)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_post_user_name_self)
        val tvPostTime: TextView = itemView.findViewById(R.id.tv_post_time_self)
        val tvPostContent: TextView = itemView.findViewById(R.id.tv_post_content_self)
        val tvPostLikeNum: TextView = itemView.findViewById(R.id.tv_like_num_self)
        val tvPostCommentNum: TextView = itemView.findViewById(R.id.tv_comment_num_self)
        val ivDeletePost: ImageView = itemView.findViewById(R.id.iv_delete_post_self)
    }

    inner class SelfPostImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar_1_self)
        val ivPostLike: ShapeableImageView = itemView.findViewById(R.id.iv_post_like_1_self)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_post_user_name_1_self)
        val tvPostTime: TextView = itemView.findViewById(R.id.tv_post_time_1_self)
        val tvPostContent: TextView = itemView.findViewById(R.id.tv_post_content_1_self)
        val tvPostLikeNum: TextView = itemView.findViewById(R.id.tv_like_num_1_self)
        val tvPostCommentNum: TextView = itemView.findViewById(R.id.tv_comment_num_1_self)
        val rvPostImage: RecyclerView = itemView.findViewById(R.id.rv_post_image_self)
        val ivDeletePost: ImageView = itemView.findViewById(R.id.iv_delete_post_1_self)
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
                    .inflate(R.layout.item_self_post, parent, false)
                SelfPostViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_self_post_image, parent, false)
                SelfPostImageViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_CONTENT -> {
                val contentHolder = holder as SelfPostViewHolder
                Glide.with(holder.itemView.context).load(postList[position].user_head).into(contentHolder.ivAvatar)
                contentHolder.tvUserName.text = postList[position].user_name
                contentHolder.tvPostTime.text = postList[position].post_last_time
                contentHolder.tvPostContent.text = postList[position].post_content.replace("\"", "")
                contentHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                contentHolder.tvPostCommentNum.text = "18"

                if (postList[position].is_like) {
                    contentHolder.ivPostLike.setImageResource(R.mipmap.post_liked)
                } else {
                    contentHolder.ivPostLike.setImageResource(R.mipmap.post_like)
                }

                contentHolder.ivPostLike.setOnClickListener {
                    onItemClickListener.onItemClick(contentHolder.itemView, position)
                }

                contentHolder.ivDeletePost.setOnClickListener {
                    val deletePost = DeletePost().newInstance(postList[position].post_id)
                    deletePost.show(fragmentManager, "deletePost")
                    val index = contentHolder.bindingAdapterPosition
                    deletePost.setOnClickListener(object : DeletePost.OnItemClickListener {
                        override fun onItemClick() {
                            postList.removeAt(index)
                            notifyDataSetChanged()
                        }
                    })
                }
            }

            else -> {
                val imageHolder = holder as SelfPostImageViewHolder
                Glide.with(holder.itemView.context).load(postList[position].user_head).into(imageHolder.ivAvatar)
                imageHolder.tvUserName.text = postList[position].user_name
                imageHolder.tvPostTime.text = postList[position].post_last_time
                imageHolder.tvPostContent.text = postList[position].post_content.replace("\"", "")
                imageHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                imageHolder.tvPostCommentNum.text = "18"

                if (postList[position].is_like) {
                    imageHolder.ivPostLike.setImageResource(R.mipmap.post_liked)
                } else {
                    imageHolder.ivPostLike.setImageResource(R.mipmap.post_like)
                }

                imageHolder.ivPostLike.setOnClickListener {
                    onItemClickListener.onItemClick(imageHolder.itemView, position)
                }

                imageHolder.ivDeletePost.setOnClickListener {
                    val deletePost = DeletePost().newInstance(postList[position].post_id)
                    deletePost.show(fragmentManager, "deletePost")
                    val index = imageHolder.bindingAdapterPosition
                    deletePost.setOnClickListener(object : DeletePost.OnItemClickListener {
                        override fun onItemClick() {
                            postList.removeAt(index)
                            notifyDataSetChanged()
                        }
                    })
                }

                val rvImage = imageHolder.rvPostImage
                val imageAdapter = PostImageAdapter(context,
                    postList[position].post_image as ArrayList<String>
                )
                rvImage.layoutManager = GridLayoutManager(appContext, 3)
                rvImage.adapter = imageAdapter
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    override fun getItemCount(): Int = postList.size
}
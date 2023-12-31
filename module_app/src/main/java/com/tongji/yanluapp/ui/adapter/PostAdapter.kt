package com.tongji.yanluapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.luck.picture.lib.utils.ToastUtils
import com.tongji.yanluapp.R
import com.tongji.lib_common.bean.PostData
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/2 14:31
 * @description:
 * @email: tongji0x208@gmail.com
 */
class PostAdapter(private val context: Context, private val postList: ArrayList<PostData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CONTENT = 1
        private const val TYPE_IMAGE = 2
    }

    private lateinit var onItemClickListener: OnItemClickListener

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar)
        val ivPostLike: ShapeableImageView = itemView.findViewById(R.id.iv_post_like)
        val tvUserName: TextView = itemView.findViewById(R.id.tv_post_user_name)
        val tvPostTime: TextView = itemView.findViewById(R.id.tv_post_time)
        val tvPostContent: TextView = itemView.findViewById(R.id.tv_post_content)
        val tvPostLikeNum: TextView = itemView.findViewById(R.id.tv_like_num)
        val tvPostCommentNum: TextView = itemView.findViewById(R.id.tv_comment_num)
    }

    inner class PostImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ShapeableImageView = itemView.findViewById(R.id.iv_post_avatar_1)
        val ivPostLike: ShapeableImageView = itemView.findViewById(R.id.iv_post_like_1)
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

    @SuppressLint("SetTextI18n")
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

                if (postList[position].is_like) {
                    contentHolder.ivPostLike.setImageResource(R.mipmap.post_liked)
                } else {
                    contentHolder.ivPostLike.setImageResource(R.mipmap.post_like)
                }

                contentHolder.ivPostLike.setOnClickListener {
                    val oldList = postList
                    onItemClickListener.onItemClick(contentHolder.itemView, position)

                    // 手动设置点赞与否，并进行差分刷新
                    postList[position].is_like = !postList[position].is_like
                    if (postList[position].is_like) {
                        postList[position].post_likes++
                    } else {
                        postList[position].post_likes--
                    }

                    val diffResult = DiffUtil.calculateDiff(MyDiffCallback(oldList, postList))
                    diffResult.dispatchUpdatesTo(this)

                    // 重新设置图标和点赞数量
                    contentHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                    if (postList[position].is_like) {
                        contentHolder.ivPostLike.setImageResource(R.mipmap.post_liked)
                    } else {
                        contentHolder.ivPostLike.setImageResource(R.mipmap.post_like)
                    }
                }
            }

            else -> {
                val imageHolder = holder as PostAdapter.PostImageViewHolder
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
                    val oldList = postList
                    onItemClickListener.onItemClick(imageHolder.itemView, position)

                    // 手动设置点赞与否，并进行差分刷新
                    postList[position].is_like = !postList[position].is_like
                    if (postList[position].is_like) {
                        postList[position].post_likes++
                    } else {
                        postList[position].post_likes--
                    }

                    val diffResult = DiffUtil.calculateDiff(MyDiffCallback(oldList, postList))
                    diffResult.dispatchUpdatesTo(this)

                    // 重新设置图标和点赞数量
                    imageHolder.tvPostLikeNum.text = postList[position].post_likes.toString()
                    if (postList[position].is_like) {
                        imageHolder.ivPostLike.setImageResource(R.mipmap.post_liked)
                    } else {
                        imageHolder.ivPostLike.setImageResource(R.mipmap.post_like)
                    }
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

    // 列表数据的差分刷新
    class MyDiffCallback(private val oldList: ArrayList<PostData>, private val newList: ArrayList<PostData>)
        : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].is_like == newList[newItemPosition].is_like
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
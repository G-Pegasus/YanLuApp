package com.tongji.yanluapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tongji.lib_common.bean.TodoResponse
import com.tongji.yanluapp.R

/**
 * ...
 * @author RQ527 (Ran Sixiang)
 * @email 1799796122@qq.com
 * @date 2023/3/14
 * @Description:
 */
class TodoAdapter : ListAdapter<TodoResponse, TodoAdapter.InnerViewHolder>(object :
    DiffUtil.ItemCallback<TodoResponse>() {
    override fun areItemsTheSame(oldItem: TodoResponse, newItem: TodoResponse): Boolean {
        return oldItem.todo_id == newItem.todo_id
    }

    override fun areContentsTheSame(oldItem: TodoResponse, newItem: TodoResponse): Boolean {
        return oldItem.detail == newItem.detail && oldItem.title == newItem.title && oldItem.time == newItem.time
    }
}) {
    inner class InnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleTv: TextView
        val mDetailTv: TextView
        init {
            itemView.run {
                mTitleTv = findViewById(R.id.tv_title_todo)
                mDetailTv = findViewById(R.id.tv_detail_todo)
                findViewById<LinearLayout>(R.id.ll_todo_item).setOnClickListener {
                    listener?.invoke(getItem(absoluteAdapterPosition))
                }
            }
            itemView.setOnLongClickListener {
                longClickListener?.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        return InnerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, null, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        holder.run {
            mTitleTv.text = getItem(position).title
            mDetailTv.text = getItem(position).detail
        }
    }

    private var listener: ((todoResponse: TodoResponse) -> Unit)? = null
    fun setOnClickedListener(listener: (todoResponse: TodoResponse) -> Unit) {
        this.listener = listener
    }

    private var longClickListener: ((todoResponse: TodoResponse) -> Unit)? = null
    fun setLongClickListener(listener: (todoResponse: TodoResponse) -> Unit) {
        longClickListener = listener
    }
}
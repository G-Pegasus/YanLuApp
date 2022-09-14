package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tongji.yanluapp.R
import com.tongji.yanluapp.bean.School

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 19:55
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SchoolAdapter(private val context: Context, private val mList: MutableList<School>) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSchool: ShapeableImageView = itemView.findViewById(R.id.iv_school)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvType: TextView = itemView.findViewById(R.id.tv_type)
        val cvSchool: CardView = itemView.findViewById(R.id.cv_school)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_school, parent, false)
        return SchoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        Glide.with(context).load(mList[position].image).into(holder.ivSchool)
        holder.tvName.text = mList[position].name
        holder.tvType.text = mList[position].type
        holder.cvSchool.setOnClickListener {
            onItemClickListener.onItemClick(
                holder.itemView, position
            )
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    override fun getItemCount(): Int = mList.size
}
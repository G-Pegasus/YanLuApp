package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tencent.mmkv.MMKV
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.dao.LikeSchoolDatabase
import com.tongji.yanluapp.app.data.LikeSchool.addSchool
import com.tongji.yanluapp.bean.School
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 19:55
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SchoolAdapter(private val context: Context, private val mList: MutableList<School>) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener
    private val mmkv = MMKV.defaultMMKV()
    private val likeSchoolDao = LikeSchoolDatabase.getDatabase(appContext).likeSchoolDao()

    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSchool: ShapeableImageView = itemView.findViewById(R.id.iv_school)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvType: TextView = itemView.findViewById(R.id.tv_type)
        val cvSchool: CardView = itemView.findViewById(R.id.cv_school)
        val ivLike: ShapeableImageView = itemView.findViewById(R.id.iv_like)
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
        holder.ivLike.setImageResource(mList[position].isLike)

        holder.ivLike.setOnClickListener {
            mList[position].isLike = R.mipmap.user_like
            likeSchoolDao.insertSchool(mList[position])
            Toast.makeText(appContext, "收藏成功，朝目标进发！", Toast.LENGTH_SHORT).show()
        }

        holder.cvSchool.setOnLongClickListener {
            onItemClickListener.onItemLongClick(
                holder.itemView, position
            )
            true
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
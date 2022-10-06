package com.tongji.yanluapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.tongji.yanluapp.R
import me.hgj.jetpackmvvm.base.appContext

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/5 9:06
 * @description:
 * @email: tongji0x208@gmail.com
 */

class PhotoShowAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<PhotoShowAdapter.InnerHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    inner class InnerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val showPhoto: PhotoView = view.findViewById(R.id.photo_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_photo, parent, false)
        return InnerHolder(view)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.apply {
            Glide.with(appContext).load(data[position]).into(showPhoto)
            showPhoto.setOnClickListener {
                onItemClickListener.onItemClick(
                    holder.itemView,
                    position
                )
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

    override fun getItemCount(): Int = data.size
}
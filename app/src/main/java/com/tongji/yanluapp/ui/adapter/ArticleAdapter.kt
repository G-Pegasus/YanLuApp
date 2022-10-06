package com.tongji.yanluapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tongji.yanluapp.R
import com.tongji.yanluapp.app.network.response.ArticleResponse

/**
 * @author: Kana (Tongji)
 * @date: 2022/10/6 19:12
 * @description:
 * @email: tongji0x208@gmail.com
 */
class ArticleAdapter(private val context: Context, private val articleList: ArrayList<ArticleResponse>)
    : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_article_title)
        val tvContent: TextView = itemView.findViewById(R.id.tv_article_content)
        val cvArticle: CardView = itemView.findViewById(R.id.cv_article)
        val tvFrom: TextView = itemView.findViewById(R.id.tv_article_from)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rec, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvTitle.text = articleList[position].recommend_title
        holder.tvContent.text = articleList[position].recommend_content
        if (articleList[position].recommend_url.contains("csdn")) {
            holder.tvFrom.text = "文章来自CSDN"
        } else if (articleList[position].recommend_url.contains("juejin")) {
            holder.tvFrom.text = "文章来自掘金"
        } else {
            holder.tvFrom.text = "文章来自知乎"
        }

        holder.cvArticle.setOnClickListener {
            onItemClickListener.onItemClick(holder.itemView, position)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun getItemCount(): Int = articleList.size
}
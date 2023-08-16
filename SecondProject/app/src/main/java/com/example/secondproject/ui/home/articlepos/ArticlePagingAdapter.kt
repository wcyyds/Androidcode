package com.example.secondproject.ui.home.articlepos

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterButton
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.LogUtil
import com.example.secondproject.data.article.Article
import com.example.secondproject.databinding.ArticleItemBinding

class ArticlePagingAdapter:PagingDataAdapter<Article, ArticlePagingAdapter.ArticleViewHolder>(COMPARATOR) {

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ArticleViewHolder(binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        val shareuserText: TextView = binding.shareuser
        val niceshareText: TextView = binding.nicesharedate
        val articleText: TextView = binding.articletitle
        val superchapterText: TextView = binding.superchaptername
        val chapterText: TextView = binding.chaptername
        val collection: ImageFilterButton = binding.imageView
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.shareuserText.setText(article.shareUser)
            holder.niceshareText.setText(article.niceShareDate)
            holder.articleText.setText(article.title)
            holder.superchapterText.setText(article.superChapterName)
            holder.chapterText.setText(article.chapterName)
        }
        holder.articleText.setOnClickListener {
            LogUtil.d("ArticlePagingAdapter","用户点击主页进入的网址" + position)
        }
        holder.collection.setOnClickListener {
            LogUtil.d("ArticlePagingAdapter","用户进行收藏的网址" + position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context)
            ,parent, false)

        return ArticleViewHolder(binding)
    }

}
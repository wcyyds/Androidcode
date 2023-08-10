package com.example.secondproject.ui.home.articlepos

import android.app.Activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.LogUtil
import com.example.secondproject.data.article.Articleslist
import com.example.secondproject.databinding.ArticleItemBinding

class ArticleAdapter(val article: Articleslist, val activity: Activity) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        val shareuserText: TextView = binding.shareuser
        val niceshareText: TextView = binding.nicesharedate
        val articleText: TextView = binding.articletitle
        val superchapterText: TextView = binding.superchaptername
        val chapterText: TextView = binding.chaptername
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        LogUtil.d("ArticleAdapter","onCreateViewHolder")
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context)
        ,parent, false)

        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return article.datas.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = article.datas[position]
        holder.shareuserText.setText(article.shareUser)
        holder.niceshareText.setText(article.niceShareDate)
        holder.articleText.setText(article.title)
        holder.superchapterText.setText(article.superChapterName)
        holder.chapterText.setText(article.chapterName)
    }

}
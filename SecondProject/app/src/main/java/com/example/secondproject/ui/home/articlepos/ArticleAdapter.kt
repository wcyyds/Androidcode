package com.example.secondproject.ui.home.articlepos

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.databinding.ArticleItemBinding

class ArticleAdapter {

    inner class ArticleViewHolder(binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        val shareuserText: TextView = binding.shareuser
        val niceshareText: TextView = binding.nicesharedate
        val articleText: TextView = binding.articletitle
        val superchapterText: TextView = binding.superchaptername
        val chapterText: TextView = binding.chaptername
    }

}
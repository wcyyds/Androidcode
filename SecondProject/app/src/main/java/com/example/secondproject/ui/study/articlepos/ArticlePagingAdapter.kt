package com.example.secondproject.ui.study.articlepos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterButton
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.LogUtil
import com.example.secondproject.MyApplication
import com.example.secondproject.R
import com.example.secondproject.data.article.Article
import com.example.secondproject.databinding.ArticleItemBinding
import com.example.secondproject.ui.study.articlepos.RoomArticle.ArticleCollection
import com.example.secondproject.ui.study.articlepos.RoomArticle.ArticleCollectionDao
import com.example.secondproject.ui.study.articlepos.RoomArticle.ArticleDatabase

class ArticlePagingAdapter(
) :
    PagingDataAdapter<Article, ArticlePagingAdapter.ArticleViewHolder>(COMPARATOR) {

    var listener: OnItemClickListener? = null

    val articleCollectionDao: ArticleCollectionDao =
        ArticleDatabase.getDatabase(MyApplication.context).articleCollectionDao();

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ArticleViewHolder(binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val shareuserText: TextView = binding.shareuser
        val niceshareText: TextView = binding.nicesharedate
        val articleText: TextView = binding.articletitle
        val superchapterText: TextView = binding.superchaptername
        val chapterText: TextView = binding.chaptername
        val collection: ImageFilterButton = binding.imageView
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        var article = getItem(position)
        var articleCollection =
            ArticleCollection(article!!.shareUser, article.title, article.id, article.link)

        holder.shareuserText.setText(article.shareUser)
        holder.niceshareText.setText(article.niceShareDate)
        holder.articleText.setText(article.title)
        holder.superchapterText.setText(article.superChapterName)
        holder.chapterText.setText(article.chapterName)
        //进行一个查找,如果收藏就变collect,如果没有就就是un_collect
        //先进行查询,如果有就不加入,如果没有就加入

        var dohave: Int? = articleCollectionDao.querArticle(articleCollection.articleid)
        LogUtil.d(
            "onBindViewHolder",
            "第一次检测: " + dohave + articleCollection.articletitle + "***" + article.title + "|||" + dohave + articleCollection.articleid + "***" + article.id
        )
        if (dohave == 1) {
            //在这里进行收藏图标的更新
            LogUtil.d("onBindViewHolder", "更新图标")
            holder.collection.setImageResource(R.drawable.ic_collect)
        } else {
            holder.collection.setImageResource(R.drawable.ic_un_collect)
        }



        holder.articleText.setOnClickListener {
            LogUtil.d("onBindViewHolder", "用户点击主页进入的网址)))" + position)
            //在这里使用webview进行应用内的跳转
            listener?.getPosition(position, article.link)

        }
        holder.collection.setOnClickListener {
            LogUtil.d("onBindViewHolder", "用户点击按钮的标题: " + article.title)
            //这次只是简单的进行收藏操作
            //先进行查询,如果有就不加入,如果没有就加入
            var dohave: Int? = articleCollectionDao.querArticle(articleCollection.articleid)
            LogUtil.d(
                "onBindViewHolder",
                "是否存在数据库中: " + dohave + articleCollection.articletitle
            )
            if (dohave == 1) {
                //已经收藏了,这里是删除
                var iddel: Int? = articleCollectionDao.deleteArticle(articleCollection.articleid)
                LogUtil.d(
                    "onBindViewHolder",
                    "用户删除的id: " + iddel + articleCollection.articletitle
                )
                //在这里进行不收藏图标更新
                holder.collection.setImageResource(R.drawable.ic_un_collect)

            } else {
                //还没有收藏,这里是添加
                var idadd: Long? = articleCollectionDao.insertArticle(articleCollection)
                LogUtil.d(
                    "onBindViewHolder",
                    "用户收藏的id: " + idadd + articleCollection.articletitle
                )
                //在这里进行收藏图标更新
                holder.collection.setImageResource(R.drawable.ic_collect)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        LogUtil.d("onCreateViewHolder", "1")

        return ArticleViewHolder(binding)
    }

}


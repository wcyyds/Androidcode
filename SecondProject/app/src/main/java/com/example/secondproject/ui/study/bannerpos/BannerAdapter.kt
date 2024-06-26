package com.example.secondproject.ui.study.bannerpos

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondproject.LogUtil
import com.example.secondproject.MyApplication
import com.example.secondproject.data.banner.Banner
import com.example.secondproject.databinding.BannerItemBinding

class BannerAdapter(val banner: Banner, val activity: Activity)
    : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            val bannerImage: ImageView = binding.bannerImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : BannerViewHolder {
        LogUtil.d("BannerAdapter","onCreateViewHolder")
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent, false)

        return BannerViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return banner.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        LogUtil.d("BannerAdapter","onBindViewHolder")
        Glide.with(MyApplication.context).load(banner[position].imagePath)
            .into(holder.bannerImage)
        holder.bannerImage.setOnClickListener {
            LogUtil.d("BannerAdapter","用户点击的position:" + position)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(banner[position].url)
            activity.startActivity(intent)
        }
    }

}
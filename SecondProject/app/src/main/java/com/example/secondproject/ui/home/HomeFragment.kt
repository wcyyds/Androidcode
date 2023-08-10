package com.example.secondproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.secondproject.LogUtil
import com.example.secondproject.databinding.FragmentHomeBinding
import com.example.secondproject.ui.home.articlepos.ArticleAdapter
import com.example.secondproject.ui.home.bannerpos.BannerAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //轮播图的位置属性
    private var currentPos = 0

    //轮播图的数量+2
    private var currentDis = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initBanner(homeViewModel)
        initArticle(homeViewModel)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //轮播图的初始化及其使用
    fun initBanner(homeViewModel: HomeViewModel){
        homeViewModel.getBanners();

        //轮播图核心
        binding.bannerviewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                LogUtil.d("HomeFragment onPageSelected","轮播图的位置" +  position)
                currentPos = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE && currentPos == currentDis-1) {
                    LogUtil.d("HomeFragment onPageScrollStateChanged", "轮播更新")
                    binding.bannerviewpager2.setCurrentItem(1, false)
                }
                if (state == ViewPager2.SCROLL_STATE_IDLE && currentPos == 0 ) {
                    LogUtil.d("HomeFragment onPageScrollStateChanged", "轮播更新")
                    binding.bannerviewpager2.setCurrentItem(currentDis-2, false)
                }
            }
        })

        homeViewModel.banner.observe(viewLifecycleOwner, Observer {
            val bannerAdapter = activity?.let { it1 -> BannerAdapter(it, it1) }
            currentDis = it.size
            LogUtil.d("HomeFragment" ,"轮播图的数量+2:" + currentDis)
            //在得到了数据之后,进行适配器的设置
            binding.bannerviewpager2.adapter = bannerAdapter
            binding.bannerviewpager2.setCurrentItem(1,false)
        })
    }

    fun initArticle(homeViewModel: HomeViewModel){
        homeViewModel.getArticles()

        homeViewModel.article.observe(viewLifecycleOwner,{
            LogUtil.d("initarticle","this:" + it.toString())
            val layoutManager = LinearLayoutManager(activity)
            binding.articlerecycleview.layoutManager = layoutManager
            val articleAdapter = activity?.let { it1 -> ArticleAdapter(it, it1) }
            binding.articlerecycleview.adapter = articleAdapter
            binding.articlerecycleview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        })
    }

}
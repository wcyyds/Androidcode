package com.example.recycleviewpractice_review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFruit()
        val recyclerView: RecyclerView = findViewById(R.id.recycleView)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        // 设置item增加和删除时的动画
        val defaultItemAnimator = DefaultItemAnimator()
        // 设置分隔线
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = defaultItemAnimator
        recyclerView.addItemDecoration(dividerItemDecoration)
        val adapter = FruitAdapter(fruitList)
        recyclerView.adapter = adapter
    }

    fun initFruit(){
        fruitList.add(Fruit("apple"))
        fruitList.add(Fruit("banana"))
        fruitList.add(Fruit("orange"))
        fruitList.add(Fruit("pear"))
        fruitList.add(Fruit("grape"))
        fruitList.add(Fruit("apple111"))
        fruitList.add(Fruit("banana111"))
        fruitList.add(Fruit("orange111"))
        fruitList.add(Fruit("pear111"))
        fruitList.add(Fruit("grape1111"))
        fruitList.add(Fruit("apple222"))
        fruitList.add(Fruit("banana222"))
        fruitList.add(Fruit("orange222"))
        fruitList.add(Fruit("pear222"))
        fruitList.add(Fruit("grape222"))
    }
}
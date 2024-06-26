package com.example.recycleviewpractice_review

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context, private var mOrientation: Int) : RecyclerView.ItemDecoration() {

    companion object {
        // 获取系统属性
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
        // 水平列表常量
        const val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        // 竖直列表常量
        const val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }

    // 分割线对象
    private var mDivider: Drawable? = null

    init {
        // 获取系统属性
        val a = context.obtainStyledAttributes(ATTRS)
        // 从属性中获取分割线
        mDivider = a.getDrawable(0)
        // 回收TypedArray对象
        a.recycle()
        // 设置方向
        setOrientation(mOrientation)
    }

    // 设置竖直或者水平方向,如果都不是则抛出异常
    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    // 绘制分割线
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    // 绘制垂直分隔线
    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    // 绘制水平分隔线
    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    // 重写方法,设置子项的偏移量
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
        } else {
            outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
        }
    }
}
package com.example.secondproject.ui.autonomy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context: Context,
             attrs: AttributeSet? = null) :  View(context, attrs) {

    class MyView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {

        private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

        init {
            mPaint.color = Color.YELLOW
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint)
            mPaint.color = Color.BLUE
            mPaint.textSize = 20f
            val text = "Hello View"
            canvas.drawText(text, 0f, height.toFloat() / 2, mPaint)
        }
    }

}
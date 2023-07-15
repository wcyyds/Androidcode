package com.example.databindinglearntest02

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class ImageViewBindingAdapter {
    companion object{
        /*
            DataBinding 布局中 ImageView 适配器
             - imageView: ImageView 参数就是布局中的 ImageView
             - url: String 参数是 ImageView 的 app:image 属性值
                 - app:image="@{imageNetwork}"
                 - imageUrl 该值是 <data> 标签中的 variable , 类型为 String

            <data>
                <variable
                    name="imageNetwork"
                    type="String" />
            </data>
         */
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, url: String) {
            // 加载网络图片
            if (!TextUtils.isEmpty(url)) {
                Picasso.get().load(url).into(imageView);
            } else {
                imageView.setBackgroundColor(Color.GREEN)
            }
        }

        /*
            DataBinding 布局中 ImageView 适配器
             - imageView: ImageView 参数就是布局中的 ImageView
             - resourceId: Int 参数是 ImageView 的 app:image 属性值
                 - app:image="@{imageLocal}"
                 - imageLocal 该值是 <data> 标签中的 variable , 类型为 int

            <data>
                <variable
                    name="imageLocal"
                    type="int" />
            </data>
         */
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, resourceId: Int) {
            imageView.setImageResource(resourceId)
        }

        /*
            DataBinding 布局中 ImageView 适配器
             - imageView: ImageView 参数就是布局中的 ImageView
             - url: String 参数是 ImageView 的 app:image 属性值
                 - app:image="@{imageNetwork}"
                 - imageUrl 该值是 <data> 标签中的 variable , 类型为 String
             - resourceId: Int 参数是 ImageView 的 app:image 属性值
                 - app:image="@{imageLocal}"
                 - imageLocal 该值是 <data> 标签中的 variable , 类型为 int

            <data>
                <variable
                    name="imageNetwork"
                    type="String" />

                <variable
                    name="imageLocal"
                    type="int" />
            </data>

            注意 : 如果是 Java 静态函数 , 注解为
            @BindingAdapter(value = ["image", "imageDefaultRes"], requireAll = false)
            Kotlin 中使用 [] 初始化数组 , Java 中使用 {} 初始化数组
         */
        @JvmStatic
        @BindingAdapter(value = ["image", "imageDefaultRes"], requireAll = false)
        fun setImage(imageView: ImageView, url: String, resourceId: Int) {
            // 加载网络图片
            if (!TextUtils.isEmpty(url)) {
                Picasso.get().load(url).into(imageView);
            } else {
                imageView.setImageResource(resourceId)
            }
        }
    }
}

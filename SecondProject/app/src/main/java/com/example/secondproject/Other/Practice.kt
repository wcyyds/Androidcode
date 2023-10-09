package com.example.secondproject.Other

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.logging.Handler

class Practice {
    val mBooleanThreadLocal = ThreadLocal<Boolean>()

    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("https://www.baidu.com")
        .build()
    val response = okHttpClient.newCall(request).execute()
    val responseData = response.body()?.string()

}
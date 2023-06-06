package okhttplearn

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.time.Duration

const val URL01 = "https://publicobject.com/helloworld.txt"

//OKhttp的异步请求
fun main(){
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(URL01)
        .build()
    val call = client.newCall(request)
    call.enqueue(object : Callback{
        override fun onFailure(call: Call, e: IOException) {
            //异步请求失败
            println("异步请求失败")
        }

        override fun onResponse(call: Call, response: Response) {
            //异步请求成功
            println("异步请求成功")
            val responseBody = response.body?.string()
            println(responseBody + "这是什么")
        }

    })

}
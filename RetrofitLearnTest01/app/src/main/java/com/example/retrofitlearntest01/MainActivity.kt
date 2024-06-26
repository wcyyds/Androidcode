package com.example.retrofitlearntest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {


    fun helloWorld(callback: (s: String) -> Unit) {
        hello { hello ->
            world { world ->
                callback("${hello}${world}")
            }
        }
    }

    fun hello(callback: (s: String) -> Unit) {
        delay(500) {
            callback("Hello, ")
        }
    }

    fun world(callback: (s: String) -> Unit) {
        delay(500) {
            callback("World!!")
        }
    }


    val delayExecutor: ScheduledThreadPoolExecutor by lazy {
        ScheduledThreadPoolExecutor(1)
    }

    fun delay(time: Long, callback: () -> Unit) {
        delayExecutor.schedule({ callback() }, time.coerceAtLeast(0), TimeUnit.MILLISECONDS)
    }


    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        helloWorld { Log.d("wang**", it) }
//        val api = ServicveCreator.create<Api>(Api::class.java)
//        Log.d("main1111", "11111")
//        api.postDataCall("").enqueue(object :  Callback<Any>{
//            override fun onResponse(call: Call<Any>, response: Response<Any>) {
//                val tring = response.body()
//                if(tring != null){
//                    Log.d("main1111", tring.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<Any>, t: Throwable) {
//                Log.d("main1111", "22222" + t.toString())
//            }
//
//        })
//        Log.d("main111", "33333")


//        Log.d("main111", "444441")
//        //创建一个Retrofit的构建器实例
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://ifanyi.iciba.com/")//设置Retrofit的基地址，所有请求都会使用这个URL作为基础。
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()//构建并返回配置好的Retrofit实例。
//        val getrequestInterface =
//            retrofit.create(GetRequest_Interface::class.java)//Retrofit实例创建一个网络请求接口GetRequest_Interface的实例
//        getrequestInterface.getCall().enqueue(object : Callback<Translation> {
//            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
//                val translation = response.body()
//                if (translation != null) {
//                    println(translation.status)
//                    println(translation.content.from)
//                    println(translation.content.to)
//                    println(translation.content.vendor)
//                    println(translation.content.out)
//                    println(translation.content.err_no)
//                }
//                Log.d("main111", "99999" + translation)
//            }
//
//            override fun onFailure(call: Call<Translation>, t: Throwable) {
//                Log.d("main111", "55555" + t.toString())
//                Log.d("main111", "55555" + t.message)
//            }
//
//        })



//        Log.d("main111", "444441")
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://ifanyi.iciba.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val postrequestInterface = retrofit.create(PostRequest_Interface::class.java)
//        val map:MutableMap<String, Any> = HashMap()
//        map["from"] = "zh"
//        map["to"] = "en"
//        map["q"] = "你好世界"
//        postrequestInterface.getCall(map).enqueue(object : Callback<Translation1>{
//            override fun onResponse(call: Call<Translation1>, response: Response<Translation1>) {
//                val translation = response.body()
//                if(translation != null){
//                    Log.d("main111", "99999" + translation.status)
//                    Log.d("main111", "99999" + translation.content.out)
//                }
//            }
//
//            override fun onFailure(call: Call<Translation1>, t: Throwable) {
//                Log.d("main111", "55555" + t.toString())
//                Log.d("main111", "55555" + t.message)
//            }
//
//        })


    }
}
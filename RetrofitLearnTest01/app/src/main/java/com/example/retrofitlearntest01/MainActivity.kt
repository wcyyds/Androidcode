package com.example.retrofitlearntest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://ifanyi.iciba.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val getrequestInterface = retrofit.create(GetRequest_Interface::class.java)
//        getrequestInterface.getCall().enqueue(object : Callback<Translation>{
//            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
//                val translation = response.body()
//                if(translation != null){
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


        Log.d("main111", "444441")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ifanyi.iciba.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val postrequestInterface = retrofit.create(PostRequest_Interface::class.java)
        val map:MutableMap<String, Any> = HashMap()
        map["from"] = "zh"
        map["to"] = "en"
        map["q"] = "你好世界"
        postrequestInterface.getCall(map).enqueue(object : Callback<Translation1>{
            override fun onResponse(call: Call<Translation1>, response: Response<Translation1>) {
                val translation = response.body()
                if(translation != null){
                    Log.d("main111", "99999" + translation.status)
                    Log.d("main111", "99999" + translation.content.out)
                }

            }

            override fun onFailure(call: Call<Translation1>, t: Throwable) {
                Log.d("main111", "55555" + t.toString())
                Log.d("main111", "55555" + t.message)
            }

        })

        

    }
}